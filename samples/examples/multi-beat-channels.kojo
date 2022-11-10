// #include /music.kojo

cleari()

val notes = Phrase(
    sa, re
)

val score =
    Score(
        200.000000,
        Part.percussion(
            Phrase(
                pbd, r, r, r,
                pbd, r, r, r,
                pbd, r, r, r,
                pbd, r, r, r
            ),
            Phrase(
                r, phc_i, phc_i, r, r,
                r, phc_i, phc_i, r, r,
                r, phc_i, phc_i, r, r,
                r, phc_i, phc_i, r, r, 
            )
        ),
        Part(
            GUITAR,
            Phrase(pa1, r, r, r, r, dha1, r, r, r, r, r, r, r, r, r, r)
        ),
        Part(
            PIANO,
            Phrase(r, r, r, sa, r, r, r, r, r, r, r, r, r, r, r, r)
        )
    )

showServerControls()
MusicPlayer.playLoop(score)
updateServerControls()
