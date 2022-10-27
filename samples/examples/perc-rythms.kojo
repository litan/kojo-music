// #include /music.kojo

cleari()

val pulse = 14 // quarter notes

val score =
    Score(
        180.0,
        PercussionPart(
            Phrase(generateBeats(Drum.CLAVES, pulse, 1)),
            Phrase(generateBeats(Drum.ACOUSTIC_BASS_DRUM, pulse, 7, 0)),
            Phrase(generateBeats(Drum.HI_BONGO, pulse, 4, 0)),
            Phrase(generateBeats(Drum.LOW_CONGA, pulse, 2, 1)),
            Phrase(generateBeats(Drum.HI_BONGO, pulse, 4, 2)),
            Phrase(generateBeats(Drum.OPEN_HI_CONGA, pulse, 2, -3)),
            Phrase(generateBeats(Drum.HI_BONGO, pulse, 4, -1)),
        )
    )

showServerControls()
MusicPlayer.playLiveLoop(score)
updateServerControls()

println(score.durationMillis)
