// #include /music.kojo

cleari()

val pulse = 16 // quarter notes

val notes = Seq(sa3_h, ga_h, pa_h, ni_h)
val notes2 = Seq(sa, ga, pa, ni, sa_i, pa, ni_i, r_h)
val tempos = Seq(150.0, 120)
val instruments = Seq(PAN_FLUTE, PIANO)

def score =
    Score(
        randomFrom(tempos),
        PercussionPart(
            Phrase(generateBeats(Drum.CLAVES, pulse, 1, 0, 1)),
            Phrase(generateBeats(Drum.LOW_CONGA, pulse, 4, 0)),
            Phrase(generateBeats(Drum.LOW_BONGO, pulse, 4, 3)),
            Phrase(generateBeats(Drum.HIGH_TOM, pulse, 2, 1, 1)),
            Phrase(generateBeats(Drum.HI_MID_TOM, pulse, 2, 2, 1)),
        ),
        InstrumentPart(
            randomFrom(instruments),
            Phrase(randomFrom(notes.permutations.toSeq) ++ randomFrom(notes2.permutations.toSeq)),
        ),
        InstrumentPart(
            randomFrom(instruments),
            Phrase(randomFrom(notes.permutations.toSeq) ++ randomFrom(notes2.permutations.toSeq)),
        ),
    )

val scoreGen = new ScoreGenerator {
    def nextScore = score
}

clearOutput()
showServerControls()
MusicPlayer.playLiveLoop(scoreGen)
updateServerControls()
