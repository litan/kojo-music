// #include /music.kojo

cleari()

val notes = Phrase(
    sa, re
)

val score =
    Score(
        120.0,
        InstrumentPart(Instrument.PAN_FLUTE, notes)
    )

showMusicServerControls()
MusicPlayer.play(score)
updateMusicServerControls()
