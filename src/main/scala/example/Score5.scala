package example

import net.kogics.kojo.music._

object Score5 {
  def main(args: Array[String]): Unit = {
    val notes = Seq(
      35, -1, -1, -1, 35, -1, -1, -1, 35, -1, -1, -1, 35, -1, -1, 35
    ).map(i => if (i == -1) Rest() else Note(i))

    val notes2 = Seq(
      60, -1, -1, -1, 60, -1, -1, -1, 60, -1, -1, -1, 60, -1, -1, 60
    ).map(i => if (i == -1) Rest() else Note(i))

    val notes3 = Seq(
      64, -1, -1, -1, 64, -1, -1, -1, 64, -1, -1, -1, 64, -1, -1, 64
    ).map(i => if (i == -1) Rest() else Note(i))

    val score = Score(
      120,
      Part.percussion(
        Phrase(notes: _*)
      ),
      Part(
        0,
        Phrase(notes2: _*),
        Phrase(notes3: _*),
      )
    )

    MusicPlayer.play(score)
  }
}
