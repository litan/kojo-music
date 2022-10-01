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
  def quarterNoteToMillis(tempo: Double) = math.round(60000 / tempo).toInt
  def toMillis(tempo: Double): Int = this match {
    case WholeNote     => quarterNoteToMillis(tempo) * 4
    case HalfNote      => quarterNoteToMillis(tempo) * 2
    case QuarterNote   => quarterNoteToMillis(tempo)
    case EighthNote    => quarterNoteToMillis(tempo) / 2
    case SixteenthNote => quarterNoteToMillis(tempo) / 4
    case NthDuration(denominator) =>
      math.round(quarterNoteToMillis(tempo) / (denominator / 4)).toInt
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
  val ii = SixteenthNote
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
    apply(drumType, duration, 0.9, 127, 0.5)
}

object Note {
  def apply(pitch: Int): Note = Note(pitch, Duration.QuarterNote)
  def apply(pitch: Int, duration: Duration): Note =
    Note(pitch, duration, 0.9, 127, 0.5)
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
  def apply(beats: Note*): MultiNote = MultiNote(beats)
  def apply(beats: collection.Seq[Note]): MultiNote = apply(beats.toSeq: _*)
}

object MultiNote {
  def apply(elems: collection.Seq[Note]): MultiNote = MultiNote(elems.toSeq: _*)
}

case class MultiNote(notes: Note*) extends MusicElem {
  def duration: Duration = durationSum(notes)
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
}

sealed abstract class Part {
  def instrument: Int
  def phrases: Seq[Phrase]
}

object Part {
  def apply(instrument: Int, phrases: Phrase*) =
    InstrumentPart(instrument, phrases: _*)

  def percussion(phrases: Phrase*) =
    PercussionPart(phrases: _*)
}

case class InstrumentPart(instrument: Int, phrases: Phrase*) extends Part

case class PercussionPart(phrases: Phrase*) extends Part {
  val instrument = 0
}

case class Score(tempo: Double, parts: Part*) {
  def durationMillis: Int = if (parts.length > 0 && parts(0).phrases.length > 0)
    parts(0).phrases(0).durationMillis(tempo)
  else
    0
}

trait ScoreGenerator {
  def nextScore: Score
}
