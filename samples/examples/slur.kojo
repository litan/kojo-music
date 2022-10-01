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

showServerControls()
MusicPlayer.play(score)
updateServerControls()
println(notes.durationDecimalForm)