// #include /music.kojo

cleari()

// pulse - number of potential quarter beats in a cycle
// numBeats - number of actual beats desired based on pulse
def generateBeats(drumType: Int, pulse: Int, numBeats: Int, shift0: Int = 0): collection.Seq[MusicElem] = {
    val realPulse = mathx.lcm(pulse, numBeats)
    val pulseMultiplier = realPulse / pulse
    val dur = Duration.nthDuration(4 * pulseMultiplier)
    val interval = realPulse / numBeats

    val beat = Beat(drumType, dur)
    val rest = Rest(dur)
    val buf = ArrayBuffer.empty[MusicElem]

    repeatFor(0 until realPulse) { idx =>
        if (idx % interval == 0) {
            buf.append(beat)
        }
        else {
            buf.append(rest)

        }
    }
    if (shift0 == 0) buf
    else if (shift0 > 0) {
        val size = buf.size
        val effectiveShift = (shift0 % size) * pulseMultiplier
        buf.drop(size - effectiveShift) ++ buf.take(size - effectiveShift)
    }
    else {
        val size = buf.size
        val effectiveShift = -(shift0 % size) * pulseMultiplier
        buf.drop(effectiveShift) ++ buf.take(effectiveShift)
    }
}

//val xx = generateBeats(Drum.HI_BONGO, 14, 4, 0)
//println(xx.zipWithIndex.filterNot { case (x, i) =>
//    x.isInstanceOf[Rest]
//})

val pulse = 14 // quarter notes

val score =
    Score(
        180.0,
        PercussionPart(
            Phrase(generateBeats(Drum.CLAVES, pulse, pulse)),
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
