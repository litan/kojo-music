// #include /music.kojo

cleari()

val pbd_x = Beat(Percussion.ACOUSTIC_BASS_DRUM, nthDuration(15))

val beats = Phrase(
    Seq.fill(60)(pbd_x)
)

val notes = Phrase(
    sa, re, ga, ma, pa, dha, ni, sa3, sa3, ni, dha, pa, ma, ga, re, sa
)

val score =
    Score(
        30.0,
        PercussionPart(beats),
        InstrumentPart(PIANO, notes)
    )

showMusicServerControls()
MusicPlayer.playLoop(score)
updateMusicServerControls()
