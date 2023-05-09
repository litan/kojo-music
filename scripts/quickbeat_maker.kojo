val template = """
  val PVAL_w = Beat(Percussion.PTYPE, w)
  val PVAL_h = Beat(Percussion.PTYPE, h)
  val PVAL = Beat(Percussion.PTYPE, q)
  val PVAL_i = Beat(Percussion.PTYPE, i)
  val PVAL_s = Beat(Percussion.PTYPE, s)
  val PVAL_t = Beat(Percussion.PTYPE, t)
"""

val percussionTypes = Seq(
    "abd" -> "ACOUSTIC_BASS_DRUM",
    "bd" -> "BASS_DRUM_1",
    "stk" -> "SIDE_STICK",
    "snr" -> "ACOUSTIC_SNARE",
    "clp" -> "HAND_CLAP",
    "hbg" -> "HI_BONGO",
    "lbg" -> "LOW_BONGO",
    "mhc" -> "MUTE_HI_CONGA",
    "ohc" -> "OPEN_HI_CONGA",
    "lhc" -> "LOW_CONGA",
    "swh" -> "SHORT_WHISTLE",
    "lwh" -> "LONG_WHISTLE",
    "cla" -> "CLAVES",
    "HWB" -> "HI_WOOD_BLOCK",
    "LWB" -> "LOW_WOOD_BLOCK",
)

clearOutput()
percussionTypes.foreach { case (pVal, pType) =>
    println(
        template
            .replaceAllLiterally("PVAL", pVal)
            .replaceAllLiterally("PTYPE", pType)
    )
}
