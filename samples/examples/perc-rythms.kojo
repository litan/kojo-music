// #include /music.kojo

cleari()

val pulse = 14 // quarter notes

val score =
    Score(
        180.0,
        PercussionPart(
            Phrase(generateBeats(Percussion.CLAVES, pulse, 1)),
            Phrase(generateBeats(Percussion.ACOUSTIC_BASS_DRUM, pulse, 7, 0)),
            Phrase(generateBeats(Percussion.HI_BONGO, pulse, 4, 0)),
            Phrase(generateBeats(Percussion.LOW_CONGA, pulse, 2, 1)),
            Phrase(generateBeats(Percussion.HI_BONGO, pulse, 4, 2)),
            Phrase(generateBeats(Percussion.OPEN_HI_CONGA, pulse, 2, -3)),
            Phrase(generateBeats(Percussion.HI_BONGO, pulse, 4, -1)),
        )
    )

showMusicServerControls()
MusicPlayer.playLiveLoop(score)
updateMusicServerControls()
