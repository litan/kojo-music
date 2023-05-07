package net.kogics.kojo

import scala.collection.mutable.ArrayBuffer

package object music {

  private[music] def durationSum(notes: Seq[Note]): Duration = {
    notes.view
      .map(_.duration)
      .reduce((d1, d2) => Duration.CompositeDuration(d1, d2))
  }

  def tie(notes: Note*): Note = {
    val firstNote = notes.head
    require(
      notes.tail.forall(note => note.pitch == firstNote.pitch),
      "All notes in a tie need to have the same pitch"
    )
    val d = durationSum(notes)
    firstNote.copy(duration = d)
  }

  def slur(notes: Note*): MusicElem = {
    MultiNoteSeq(
      notes.view
        .take(notes.length - 1)
        .map(note => note.copy(length = 1))
        .appended(notes.last)
        .toSeq
    )
  }

  // pulse - number of potential quarter beats in a cycle
  // numBeats - number of actual beats desired based on pulse
  def generateBeats(
      drumType: Int,
      pulse: Int,
      numBeats: Int,
      shift0: Int = 0,
      durationShortenFactor: Int = 1
  ): collection.Seq[MusicElem] = {
    generateElems(
      { d => Beat(drumType, d) },
      pulse,
      numBeats,
      shift0,
      durationShortenFactor
    )
  }

  def generateNotes(
      pitch: Int,
      pulse: Int,
      numBeats: Int,
      shift0: Int = 0,
      durationShortenFactor: Int = 1
  ): collection.Seq[MusicElem] = {
    generateElems({ d => Note(pitch, d) }, pulse, numBeats, shift0, durationShortenFactor)
  }

  def generateElems(
      elemMaker: Duration => MusicElem,
      pulse: Int,
      numBeats: Int,
      shift0: Int,
      durationShortenFactor: Int
  ): collection.Seq[MusicElem] = {
    val realPulse = Util.lcm(pulse * durationShortenFactor, numBeats)
    val pulseMultiplier = realPulse / pulse
    val dur = Duration.nthDuration(4 * pulseMultiplier)
    val interval = realPulse / numBeats

    val beat = elemMaker(dur)
    val rest = Rest(dur)
    val buf = ArrayBuffer.empty[MusicElem]

    (0 until realPulse).foreach { idx =>
      if (idx % interval == 0) {
        buf.append(beat)
      }
      else {
        buf.append(rest)

      }
    }
    if (shift0 == 0) buf
    else if (shift0 > 0) {
      val size = buf.size
      val effectiveShift = (shift0 % size) * pulseMultiplier
      buf.drop(size - effectiveShift) ++ buf.take(size - effectiveShift)
    }
    else {
      val size = buf.size
      val effectiveShift = -(shift0 % size) * pulseMultiplier
      buf.drop(effectiveShift) ++ buf.take(effectiveShift)
    }
  }
}
