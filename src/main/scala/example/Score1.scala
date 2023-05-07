package example
import net.kogics.kojo.music._

object Score1 {
  def main(args: Array[String]): Unit = {
    val notes = Seq(60, -1, 62, -1, 64, -1, 65, -1).map(n => if (n == -1) Rest() else Note(n))
    val notes2 = Seq(60, 62, 64, 65).map(Note(_))

    val score = Score(
      120,
      Part(
        0, // instrument
        Phrase(notes: _*)
      ),
      Part.percussion(
        Phrase(notes: _*)
      )
    )

    MusicPlayer.play(score)

    Thread.sleep(2100)

    val score2 = Score(
      60,
      Part(
        0, // instrument
        Phrase(notes2: _*)
      ),
      Part.percussion(
        Phrase(notes2: _*)
      )
    )

    MusicPlayer.playNext(score2)
  }
}
