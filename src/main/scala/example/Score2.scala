package example

import net.kogics.kojo.music._

object Score2 {
  def main(args: Array[String]): Unit = {
    val notes = Seq.fill(16)(36).map(Note(_))

    val score = Score(
      120,
      Part.percussion(
        Phrase(notes: _*)
      )
    )

    MusicPlayer.play(score)
  }
}
