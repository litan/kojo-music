// #include /music.kojo

cleari()

val beats = Phrase(
    pbd, r, pbd, r, pbd, r, pbd, phc, pbd, r, pbd, r, pbd, r, pbd, phc
)

val notes = Phrase(
    sa, re, ga, ma, pa, dha, ni, sa3, sa3, ni, dha, pa, ma, ga, re, sa
)

val score =
    Score(
        120.0,
        PercussionPart(beats),
        InstrumentPart(PIANO, notes)
    )


showMusicServerControls()
MusicPlayer.playLoop(score)
updateMusicServerControls()
