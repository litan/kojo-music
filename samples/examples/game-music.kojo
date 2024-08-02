// #include /music.kojo

val rcy_h = Beat(Percussion.RIDE_CYMBAL_1, h)

def chordSoft(notes: Note*) = {
    MultiNotePar(notes.map(n => n.copy(dynamic=60)))
}

def Cmaj7_w = chordSoft(c4_w, e4_w, g4_w, b4_w)
def Am7_w = chordSoft(a3_w, c4_w, e4_w, g4_w)
def Fmaj7_w = chordSoft(f3_w, a3_w, c4_w, e4_w)
def G7_sus4_w = chordSoft(g3_w, c4_w, d4_w, f4_w)

cleari()
val score =
    Score(
        240.0,
        Part.percussion(
            Phrase(rcy_h, r_h, r_h, r_h, rcy_h, r_h, r_h, r_h) +
                Phrase(rcy_h, r_h, r_h, r_h, rcy_h, r_h, r_h, r_h)
        ),
        Part(
            Instrument.SYNTH_BRASS_1,
            Phrase(c4, e4, g4, b4, c4, e4, g4, b4, a4, c5, e4, g4, a4, g4, e4, c4) +
                Phrase(f4, a4, c5, e4, f4, a4, c4, e4, g4, c5, d4, f4, g4, f4, d4, c4)
        ),
        Part(
            Instrument.SYNTH_BASS,
            Phrase(c4_w, c4_w, a4_w, a4_w) +
                Phrase(f4_w, f4_w, g4_w, g4_w)

        ),
        Part(
            Instrument.ACOUSTIC_GRAND,
            Phrase(Cmaj7_w, r_w, Am7_w, r_w) +
                Phrase(Fmaj7_w, r_w, G7_sus4_w, r_w)

        ),
    )
showMusicServerControls()
MusicPlayer.playLiveLoop(score)
updateMusicServerControls()