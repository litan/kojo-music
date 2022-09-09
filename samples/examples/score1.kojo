// #include /music.kojo

cleari()

val beats = Phrase(
    pbd, r, pbd, r, pbd, r, pbd, phc, pbd, r, pbd, r, pbd, r, pbd, phc
)

val notes = Phrase(
    c4, d4, e4, f4, g4, a4, b4, c5, c5, b4, a4, g4, f4, e4, d4, c4
)

val score =
    Score(
        120.0,
        PercussionPart(beats),
        InstrumentPart(PAN_FLUTE, notes)
    )

val score2 =
    Score(
        120.0,
        InstrumentPart(PIANO, notes)
    )

showServerControls()
MusicPlayer.play(score)
pause(2)
MusicPlayer.playAlso(score2)

