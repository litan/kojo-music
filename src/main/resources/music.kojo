import net.kogics.kojo.music._
import Duration._
import QuickNote._
import QuickSwara._
import QuickBeat._
import Instrument._

val vertGap = 3

val startMusicServerButton: Button = {
    lazy val btn: Button = Button("Srv Up") {
        if (!MusicPlayer.started) {
            println("\nStarting music server. This might take a few seconds...")
            schedule(0.1) {
                MusicPlayer.startAsNeeded(true)
                btn.setEnabled(false)
            }
        }
    }
    btn.setEnabled(!MusicPlayer.started)
    btn
}

val stopMusicServerButton = {
    lazy val btn: Button = Button("Srv Dn") {
        if (MusicPlayer.started) {
            stopMusic()
            MusicPlayer.stop()
            startMusicServerButton.setEnabled(true)
            btn.setEnabled(false)
        }
    }
    btn.setEnabled(MusicPlayer.started)
    btn
}

val stopButton = Button("Stop Playing") {
    stopMusic()
}

def stopMusic() {
    MusicPlayer.stopPlaying()
}

val updateButton = Button("Update Srv Up/Dn") {
    updateServerControls()
}

val controlPanel = {
    ColPanel(
        ColPanel.verticalGap(vertGap),
        RowPanel(stopButton),
        ColPanel.verticalGap(vertGap),
        RowPanel(updateButton),
        ColPanel.verticalGap(vertGap),
        RowPanel(
            startMusicServerButton,
            RowPanel.horizontalGap(10),
            stopMusicServerButton
        ),
    )
}

val controls = Picture.widget(controlPanel)

def showServerControls() {
    val cb = canvasBounds
    draw(controls.withTranslation(cb.x + 20, cb.y + 20))
}

def updateServerControls() {
    val started = MusicPlayer.started
    startMusicServerButton.setEnabled(!started)
    stopMusicServerButton.setEnabled(started)
}
