package example

import net.kogics.kojo.music._

object Score6 {
  def main(args: Array[String]): Unit = {
    import QuickBeat._
    import QuickNote._
    val score = Score(
      120,
      Part.percussion(
        Phrase(pbd, r, pbd, r)
      ),
      Part.percussion(
        Phrase(r, phc, phc, phc)
      ),
      Part(
        Instrument.PIANO,
        Phrase(c4, d4, c4, d4)
      )
    )

    MusicPlayer.play(score)

    Thread.sleep(5000 + 2000 + 500)

    val score2 = Score(
      120,
      Part(
        Instrument.GUITAR,
        Phrase(c4, d4, c4, d4)
      )
    )

    MusicPlayer.play(score2)
  }
}
