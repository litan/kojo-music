package example
import net.kogics.kojo.music._

object NoteGen {
  def main(args: Array[String]): Unit = {
    val notes = generateNotes(Pitch.c3, 14, 1, 0, 3)
    println(Phrase(notes).durationMillis(120))
  }
}
