// #include /music.kojo

cleari()

val notes = Phrase(
    re, tie(sa, sa, sa), re, re, re
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