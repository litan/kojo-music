package example

import net.kogics.kojo.music._

object Score7 {
  def main(args: Array[String]): Unit = {
    import QuickNote._
    val score = Score(
      60,
      Part(
        0, // instrument
        Phrase(
          c4,
          d4_i, d4_i,
          e4_s, e4_s, e4_s, e4_s,
          f4_t, f4_t, f4_t, f4_t,f4_t, f4_t,f4_t, f4_t,
          g4, a4, b4, c5
        )
      )
    )

    MusicPlayer.playLoop(score)
  }
}
