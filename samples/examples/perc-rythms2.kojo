// #include /music.kojo

cleari()

//val xx = generateBeats(Drum.HI_BONGO, 14, 4, 0)
//println(xx.zipWithIndex.filterNot { case (x, i) =>
//    x.isInstanceOf[Rest]
//})

val pulse = 14 // quarter notes

val score =
    Score(
        180.0,
        //        InstrumentPart(
        //            Instrument.BASS,
        //            Phrase(generateNotes(Pitch.c3, pulse, 1, 0, 1))
        //        ),
        InstrumentPart(
            Instrument.NYLON_GUITAR,
            Phrase(generateNotes(Pitch.E2, pulse, 7, 0)),
            Phrase(generateNotes(Pitch.D4, pulse, 4, 0)),
            Phrase(generateNotes(Pitch.AS1, pulse, 2, 1)),
            Phrase(generateNotes(Pitch.D5, pulse, 4, 2)),
            Phrase(generateNotes(Pitch.A2, pulse, 2, -3)),
            Phrase(generateNotes(Pitch.G4, pulse, 4, -1)),
        ),
        InstrumentPart(
            Instrument.PIANO,
            Phrase(generateNotes(Pitch.AS1, pulse, 2, 1, 4)),
            Phrase(generateNotes(Pitch.G4, pulse, 4, -1)),
        ),
        PercussionPart(
//            Phrase(generateBeats(Drum.CLAVES, pulse, pulse)),
            Phrase(generateBeats(Drum.ACOUSTIC_BASS_DRUM, pulse, 7, 0)),
            Phrase(generateBeats(Drum.ACOUSTIC_BASS_DRUM, pulse, 4, 0)),
            Phrase(generateBeats(Drum.HIGH_TOM, pulse, 4, 2)),
            Phrase(generateBeats(Drum.HAND_CLAP, pulse, 2, -3)),
            Phrase(generateBeats(Drum.HI_BONGO, pulse, 4, -1)),
        )
    )

showServerControls()
MusicPlayer.playLiveLoop(score)
updateServerControls()

println(score.durationMillis)
