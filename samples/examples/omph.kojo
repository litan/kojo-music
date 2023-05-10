// #include /music.kojo

cleari()

val mPhrase1 = Phrase(
    tie(re, re), re, re, re, re, re, ga, pa, ga, ga, ga, ga, re, sa, r,
)

val mPhrase2 = Phrase(
    tie(sa, sa), ga, ga, ga, re, re, r, tie(re, re), re, sa, sa_i, re_i, sa, dha1, r
)

val allRests = Phrase(r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r)

val score =
    Score(
        150.0,
        Part.percussion(
            allRests + allRests
        ),
        Part(
            GUITAR,
            Phrase(re1, r, r, r, re1, r, r, r, ga1, r, r, r, ga1, r, r, r) +
                Phrase(sa1, r, r, r, sa1, r, r, r, re1, r, r, r, re1, r, r, r),
            Phrase(ma1, r, r, r, ma1, r, r, r, pa1, r, r, r, pa1, r, r, r) +
                Phrase(ga1, r, r, r, ga1, r, r, r, ma1, r, r, r, ma1, r, r, r),
            Phrase(dha1, r, r, r, dha1, r, r, r, ni1, r, r, r, ni1, r, r, r) +
                Phrase(pa1, r, r, r, pa1, r, r, r, dha1, r, r, r, dha1, r, r, r)
        ),
        Part(
            PIANO_ACCORDION,
            mPhrase1 + mPhrase2
        )
    )

showMusicServerControls()
MusicPlayer.playLiveLoop(score)
updateMusicServerControls()
