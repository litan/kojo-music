// #include /music.kojo

cleari()

val pulse = 14 // quarter notes

val score =
    Score(
        180.0,
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
            Phrase(generateBeats(Percussion.CLAVES, pulse, 1)),
            Phrase(generateBeats(Percussion.ACOUSTIC_BASS_DRUM, pulse, 7, 0)),
            Phrase(generateBeats(Percussion.ACOUSTIC_BASS_DRUM, pulse, 4, 0)),
            Phrase(generateBeats(Percussion.HIGH_TOM, pulse, 4, 2)),
            Phrase(generateBeats(Percussion.HAND_CLAP, pulse, 2, -3)),
            Phrase(generateBeats(Percussion.HI_BONGO, pulse, 4, -1)),
        )
    )

showMusicServerControls()
MusicPlayer.playLiveLoop(score)
updateMusicServerControls()
