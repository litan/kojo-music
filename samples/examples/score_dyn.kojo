// #include /music.kojo

cleari()

val notes = Seq(sa, re, ga, pa, sa3)
val instruments = Seq(Instrument.PIANO, Instrument.PAN_FLUTE)
val tempos = Seq(60.0, 120)

val scoreMaker = new ScoreGenerator {
    def nextScore = {
        Score(
            randomFrom(tempos),
            InstrumentPart(
                randomFrom(instruments),
                Phrase(randomFrom(notes.permutations.toList))
            )
        )
    }
}

showMusicServerControls()
MusicPlayer.playLoop(scoreMaker)
updateMusicServerControls()
