// #include /music.kojo

cleari()

val notes = Phrase(
    sa, re, ga, ma, slur(sa, re, ga, ma), pa
)

val score =
    Score(
        120.0,
        InstrumentPart(Instrument.PIANO_ACCORDION, notes)
    )

showMusicServerControls()
MusicPlayer.play(score)
updateMusicServerControls()
println(notes.durationDecimalForm)