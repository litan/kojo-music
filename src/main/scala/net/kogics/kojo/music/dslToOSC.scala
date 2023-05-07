/*
 * Copyright (C) 2022 Lalit Pant <pant.lalit@gmail.com>
 *
 * The contents of this file are subject to the GNU General Public License
 * Version 3 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.gnu.org/copyleft/gpl.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 */
package net.kogics.kojo.music

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._

import com.illposed.osc.OSCBundle
import com.illposed.osc.OSCMessage
import com.illposed.osc.OSCPacket

object OSCBundleGenerator {
  def message(address: String, args: Seq[Any]) =
    new OSCMessage(address, args.asJava)

  def noteToOSCPacket(
      note: Note,
      channel: Int,
      offset: Int,
      tempo: Double
  ): OSCPacket = {
    val durationMillis = note.duration.toMillis(tempo)
    val lengthMillis = (durationMillis * note.length).toInt
    message(
      s"/track/$channel/midi/note",
      Seq(offset, note.pitch, durationMillis, lengthMillis, note.dynamic)
    )
  }

  def phrasesToOSCPackets(
      phrases: Seq[Phrase],
      channel: Int,
      tempo: Double
  ): collection.Seq[OSCPacket] = {
    def dEqual(n1: Double, n2: Double, tolerance: Double) =
      (n1 - n2).abs < tolerance

    val packets = ArrayBuffer.empty[OSCPacket]

    def addNotePackets(note: Note, offset0: Int): Int = {
      var offset = offset0
      if (!dEqual(note.pan, 0.5, 0.01)) {
        packets.append(
          message(
            s"/track/${channel}/midi/panning",
            Seq(offset, (note.pan * 127).toInt)
          )
        )
      }
      val packet = noteToOSCPacket(note, channel, offset, tempo)
      offset += note.duration.toMillis(tempo)
      packets.append(packet)
      if (!dEqual(note.pan, 0.5, 0.01)) {
        packets.append(
          message(s"/track/${channel}/midi/panning", Seq(offset, 64))
        )
      }
      offset
    }

    phrases.foreach { phrase =>
      var offset = 0
      phrase.elems.foreach {
        case note: Note =>
          offset = addNotePackets(note, offset)
        case rest: Rest =>
          offset += rest.duration.toMillis(tempo)
        case mn: MultiNoteSeq =>
          mn.notes.foreach { note =>
            offset = addNotePackets(note, offset)
          }
        case pmn: MultiNotePar =>
          pmn.notes.foreach { note =>
            addNotePackets(note, offset)
          }
          offset += pmn.duration.toMillis(tempo)
      }
      if (phrase.elems.nonEmpty) {
        phrase.elems.last match {
          case r: Rest =>
            packets.append(
              noteToOSCPacket(
                Note(0, r.duration, 0.9, 0, 0.5),
                channel,
                offset - r.duration.toMillis(tempo),
                tempo
              )
            )
          case _ =>
        }
      }
    }
    packets
  }

  def instrumentPartToOSCPackets(
      part: Part,
      tempo: Double,
      channel: Int
  ): Seq[OSCPacket] = {
    val start =
      IndexedSeq(
        message(s"/track/${channel}/midi/patch", Seq(0, part.instrument)),
        message(s"/track/${channel}/midi/volume", Seq(0, 100)),
        message(s"/track/${channel}/midi/panning", Seq(0, 64))
      )
    start
      .appendedAll(phrasesToOSCPackets(part.phrases, channel, tempo))
  }

  def percussionPartToOSCPackets(
      part: Part,
      tempo: Double
  ): Seq[OSCPacket] = {
    val channel = percussionChannel
    val start = {
      if (percussionSeen)
        IndexedSeq()
      else {
        percussionSeen = true
        IndexedSeq(
          message(s"/track/${channel}/midi/patch", Seq(0, part.instrument)),
          message(s"/track/${channel}/midi/volume", Seq(0, 100)),
          message(s"/track/${channel}/midi/panning", Seq(0, 64)),
          message(s"/track/${channel}/midi/percussion", Seq(0))
        )
      }
    }

    start
      .appendedAll(phrasesToOSCPackets(part.phrases, channel, tempo))
  }

  def partToOSCPackets(
      part: Part,
      tempo: Double,
      nextChannel: Int
  ): Seq[OSCPacket] = part match {
    case _: InstrumentPart =>
      instrumentPartToOSCPackets(part, tempo, nextChannel)
    case _: PercussionPart =>
      percussionPartToOSCPackets(part, tempo)
  }

  val percussionChannel = 9
  var currChannel: Int = _
  var percussionSeen: Boolean = _

  def resetCurrChannel(): Unit = {
    currChannel = 1
    percussionSeen = false
  }

  def incrementCurrChannel(): Unit = {
    currChannel += 1
    if (currChannel == percussionChannel) {
      currChannel += 1
    }
    if (currChannel == 16) {
      currChannel = 1
    }
  }

  def clearMessage = message("/system/clear", Seq())
  def shutdownMessage(howMuchLater: Int) =
    message("/system/shutdown", Seq(howMuchLater))

  def pingMessage = message("/ping", Seq())

  def scoreMessagesWithClear(score: Score): Seq[OSCPacket] = synchronized {
    resetCurrChannel()
    IndexedSeq(clearMessage)
      .appendedAll(scoreMessages(score))
  }

  def scoreMessagesForPlayingNextInitChannels(score: Score): Seq[OSCPacket] =
    synchronized {
      resetCurrChannel()
      scoreMessages(score)
    }

  def scoreMessagesForPlayingNowNextChannels(score: Score): Seq[OSCPacket] =
    synchronized {
      score.parts.find(p => p.isInstanceOf[PercussionPart]) match {
        case Some(perc) =>
          assert(
            false,
            "Percussion part is not allowed for playing concurrently"
          )
        case None =>
      }
      scoreMessages(score)
    }

  private def scoreMessages(score: Score): Seq[OSCPacket] = {
    val tempo = score.tempo
    score.parts
      .flatMap { part =>
        val ret = partToOSCPackets(part, tempo, currChannel)
        if (!part.isInstanceOf[PercussionPart]) {
          incrementCurrChannel()
        }
        ret
      }
      .appended(message("/system/play", Seq()))
  }

  def bundle(msgs: Seq[OSCPacket]): OSCBundle =
    new OSCBundle(msgs.asJava)

  def scoreToOSCBundleWithClear(score: Score): OSCBundle = bundle(
    scoreMessagesWithClear(score)
  )
  def scoreToOSCBundleNextInitChannels(score: Score): OSCBundle = bundle(
    scoreMessagesForPlayingNextInitChannels(score)
  )

  def scoreToOSCBundleNowNextChannels(score: Score): OSCBundle = bundle(
    scoreMessagesForPlayingNowNextChannels(score)
  )

  def stopOSCBundle(): OSCBundle = bundle(Seq(clearMessage))

  def shutdownOSCBundle(howMuchLater: Int): OSCBundle = bundle(
    Seq(shutdownMessage(howMuchLater))
  )

  def pingOSCBundle(): OSCBundle = bundle(Seq(pingMessage))
}
