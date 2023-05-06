val template = """
  val NXl_w = Note(Pitch.NX, w)
  val NXl_h = Note(Pitch.NX, h)
  val NXl = Note(Pitch.NX, q)
  val NXl_i = Note(Pitch.NX, i)
  val NXl_s = Note(Pitch.NX, s)
  val NXl_t = Note(Pitch.NX, t)
"""

val noteNames = Seq(
    "A",
    "AS",
    "BF",
    "B",
    "C",
    "CS",
    "DF",
    "D",
    "DS",
    "EF",
    "E",
    "F",
    "FS",
    "GF",
    "G",
    "GS",
    "AF"
)

val allNoteNames =
    (3 to 5).foldLeft(IndexedSeq.empty[String]) { (currSeq, currOctave) =>
        currSeq ++ noteNames.map(_ + currOctave.toString)
    }

val data = allNoteNames

clearOutput()
data.foreach { noteName =>
    println(
        template
            .replaceAllLiterally("NXl", noteName.toLowerCase)
            .replaceAllLiterally("NX", noteName)
    )
}
