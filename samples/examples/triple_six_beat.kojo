// #include /music.kojo

cleari()

val pbd_12 = Beat(Percussion.ACOUSTIC_BASS_DRUM, nthDuration(12))
val pbd_24 = Beat(Percussion.ACOUSTIC_BASS_DRUM, nthDuration(24))

def triple = MultiBeat(Seq.fill(3)(pbd_12))
def six = MultiBeat(Seq.fill(6)(pbd_24))

val beats = Phrase(
    pbd, r, triple, r, six, r, triple, phc, pbd, r, triple, r, six, r, triple, phc
)

val notes = Phrase(
    sa, re, ga, ma, pa, dha, ni, sa3, sa3, ni, dha, pa, ma, ga, re, sa
)

val score =
    Score(
        60.0,
        PercussionPart(beats),
        InstrumentPart(PIANO, notes)
    )

showServerControls()
MusicPlayer.playLoop(score)
updateServerControls()
