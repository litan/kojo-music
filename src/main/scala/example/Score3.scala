package example

import net.kogics.kojo.music._

object Score3 {
  def main(args: Array[String]): Unit = {
    val notes = Seq.fill(16)(60).map(Note(_))
    val notes2 = Seq.fill(16)(64).map(Note(_))
    val notes3 = Seq.fill(16)(67).map(Note(_))

    val score = Score(
      60,
      Part(
        0, // instrument
        Phrase(notes: _*),
        Phrase(notes2: _*),
        Phrase(notes3: _*),
      ),
    )

    MusicPlayer.play(score)
  }
}
