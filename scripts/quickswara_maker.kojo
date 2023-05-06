val template = """
  val NXl_w = Note(SwaraPitch.NX, w)
  val NXl_h = Note(SwaraPitch.NX, h)
  val NXl = Note(SwaraPitch.NX, q)
  val NXl_i = Note(SwaraPitch.NX, i)
  val NXl_s = Note(SwaraPitch.NX, s)
  val NXl_t = Note(SwaraPitch.NX, t)
"""

val swaraNames = Seq(
    "Sa",
    "Re_komal",
    "Re",
    "Ga_komal",
    "Ga",
    "Ma",
    "Ma_tivra",
    "Pa",
    "Dha_komal",
    "Dha",
    "Ni_komal",
    "Ni"
)

val allSwaraNames =
    (1 to 3).foldLeft(IndexedSeq.empty[String]) { (currSeq, currOctave) =>
        currSeq ++ swaraNames.map { n =>
            val suffix = if (currOctave == 2) "" else currOctave.toString
            n + suffix
        }
    }

val data = allSwaraNames

clearOutput()
data.foreach { noteName =>
    println(
        template
            .replaceAllLiterally("NXl", noteName.toLowerCase)
            .replaceAllLiterally("NX", noteName)
    )
}
