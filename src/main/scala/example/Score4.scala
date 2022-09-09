package example

import net.kogics.kojo.music._

object Score4 {
  def main(args: Array[String]): Unit = {
    val notes = Seq.fill(16)(36).map(Note(_))
    val notes2 = Seq.fill(16)(39).map(Note(_))

    val score = Score(
      60,
      Part.percussion(
        Phrase(notes: _*),
        Phrase(notes2: _*),
      )
    )

    MusicPlayer.play(score)
  }
}
