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

trait MusicElem {
  def duration: Duration
}

sealed abstract class Duration {
  import Duration._
  private def quarterNoteToMillis(tempo: Double) = 60000 / tempo
  private def round(d: Double): Int = math.round(d).toInt
  def toMillis(tempo: Double): Int = this match {
    case WholeNote     => round(quarterNoteToMillis(tempo) * 4)
    case HalfNote      => round(quarterNoteToMillis(tempo) * 2)
    case QuarterNote   => round(quarterNoteToMillis(tempo))
    case EighthNote    => round(quarterNoteToMillis(tempo) / 2)
    case SixteenthNote => round(quarterNoteToMillis(tempo) / 4)
    case NthDuration(denominator) =>
      round(quarterNoteToMillis(tempo) / (denominator / 4))
    case CompositeDuration(d1, d2) => d1.toMillis(tempo) + d2.toMillis(tempo)
  }

  def toDecimalForm: Double = this match {
    case WholeNote                 => 1
    case HalfNote                  => 0.5
    case QuarterNote               => 0.25
    case EighthNote                => 0.125
    case SixteenthNote             => 1 / 16.0
    case NthDuration(denominator)  => 1 / denominator
    case CompositeDuration(d1, d2) => d1.toDecimalForm + d2.toDecimalForm
  }

  def +(other: Duration) = CompositeDuration(this, other)
}

object Duration {
  case object WholeNote extends Duration
  case object HalfNote extends Duration
  case object QuarterNote extends Duration
  case object EighthNote extends Duration
  case object SixteenthNote extends Duration
  case class NthDuration(denominator: Double) extends Duration
  case class CompositeDuration(d1: Duration, d2: Duration) extends Duration

  val w = WholeNote
  val h = HalfNote
  val q = QuarterNote
  val i = EighthNote
  val s = SixteenthNote
  val t = nthDuration(32)
  def nthDuration(denominator: Double) = NthDuration(denominator)
}

object Beat {
  def apply(
      drumType: Int,
      duration: Duration,
      length: Double,
      dynamic: Int,
      pan: Double
  ): Note = Note(drumType, duration, length, dynamic, pan)
  def apply(drumType: Int): Note = apply(drumType, Duration.QuarterNote)
  def apply(drumType: Int, duration: Duration): Note =
    apply(drumType, duration, 0.9, 100, 0.5)
}

object Note {
  def apply(pitch: Int): Note = Note(pitch, Duration.QuarterNote)
  def apply(pitch: Int, duration: Duration): Note =
    Note(pitch, duration, 0.9, 100, 0.5)
}

case class Note(
    pitch: Int,
    duration: Duration,
    length: Double, // fraction of duration
    dynamic: Int, // volume
    pan: Double
) extends MusicElem

case class Rest(duration: Duration = Duration.QuarterNote) extends MusicElem

object MultiBeat {
  def apply(beats: Note*): MultiNoteSeq = MultiNoteSeq(beats)
  def apply(beats: collection.Seq[Note]): MultiNoteSeq = apply(beats.toSeq: _*)
}

object MultiNoteSeq {
  def apply(elems: collection.Seq[Note]): MultiNoteSeq = MultiNoteSeq(
    elems.toSeq: _*
  )
}

case class MultiNoteSeq(notes: Note*) extends MusicElem {
  def duration: Duration = durationSum(notes)
}

object MultiNotePar {
  def apply(elems: collection.Seq[Note]): MultiNotePar = MultiNotePar(
    elems.toSeq: _*
  )
}

case class MultiNotePar(notes: Note*) extends MusicElem {
  val ordering = new Ordering[Duration] {
    def compare(x: Duration, y: Duration): Int =
      x.toMillis(60).compare(y.toMillis(60))
  }
  def duration: Duration = notes.map(_.duration).max(ordering)
}

object Phrase {
  def apply(elems: collection.Seq[MusicElem]): Phrase = Phrase(elems.toSeq: _*)
}

case class Phrase(elems: MusicElem*) {
  def durationMillis(tempo: Double): Int = elems.foldLeft(0) { (d, e) =>
    d + e.duration.toMillis(tempo)
  }

  def durationDecimalForm: Double = elems.foldLeft(0.0) { (d, e) =>
    d + e.duration.toDecimalForm
  }

  def +(other: Phrase) = Phrase(elems ++ other.elems)
}

sealed abstract class Part {
  def instrument: Int
  def phrases: Seq[Phrase]
  def durationMillis(tempo: Double): Int = {
    val phraseDurationMillis = phrases.map(p => p.durationMillis(tempo))
    if (phraseDurationMillis.size > 0) phraseDurationMillis.max else 0
  }

  def showDurationMillis(tempo: Double): Unit = {
    println("---Part phrase millis:")
    var max = 0
    phrases.zipWithIndex.foreach {
      case (p, i) =>
        val dm = p.durationMillis(tempo)
        max = math.max(dm, max)
        println(s"Phrase $i - $dm")
    }
    println(s"Part millis max - $max")
  }
}

object Part {
  def apply(instrument: Int, phrases: Phrase*): Part =
    InstrumentPart(instrument, phrases: _*)

  def apply(instrument: Int, phrases: collection.Seq[Phrase]): Part =
    apply(instrument, phrases.toSeq: _*)

  def percussion(phrases: Phrase*) =
    PercussionPart(phrases: _*)
}

case class InstrumentPart(instrument: Int, phrases: Phrase*) extends Part

case class PercussionPart(phrases: Phrase*) extends Part {
  val instrument = 0
}

case class Score(tempo: Double, parts: Part*) {
  val durationMillis: Int = {
    val partDurationMillis =
      parts
        .map(p => p.durationMillis(tempo))
        .filter(d => d != 0)

    if (partDurationMillis.distinct.length != 1) {
      println("Score Warning - part duration millis are not the same!")
      println("Part duration millis:")
      partDurationMillis.zipWithIndex.foreach {
        case (d, i) =>
          println(s"Part $i - $d")
      }
      parts.foreach(_.showDurationMillis(tempo))
    }
    partDurationMillis.max
  }
}

trait ScoreGenerator {
  def nextScore: Score
}
