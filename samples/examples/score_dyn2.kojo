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
            Phrase(generateBeats(Percussion.CLAVES, pulse, 1, 0, 1)),
            Phrase(generateBeats(Percussion.LOW_CONGA, pulse, 4, 0)),
            Phrase(generateBeats(Percussion.LOW_BONGO, pulse, 4, 3)),
            Phrase(generateBeats(Percussion.HIGH_TOM, pulse, 2, 1, 1)),
            Phrase(generateBeats(Percussion.HI_MID_TOM, pulse, 2, 2, 1)),
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
showMusicServerControls()
MusicPlayer.playLiveLoop(scoreGen)
updateMusicServerControls()
