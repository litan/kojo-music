package example
import net.kogics.kojo.music.QuickSwara._
import net.kogics.kojo.music._

object Slur1 {
  def main(args: Array[String]): Unit = {
    val notes = Phrase(
      sa, re, ga, ma, slur(sa, re, ga, ma), pa
    )

    val score =
      Score(
        120.0,
        InstrumentPart(Instrument.PIANO_ACCORDION, notes)
      )

    MusicPlayer.play(score)
  }
}
