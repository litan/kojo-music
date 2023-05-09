import net.kogics.kojo.music._
import Duration._
import QuickNote._
import QuickSwara._
import QuickBeat._
import Instrument._

val vertGap = 3

def stopMusic() {
    if (MusicPlayer.started) {
        MusicPlayer.stopPlaying()
    }
}

val startMusicServerButton: Button = {
    lazy val btn: Button = Button("Srv Up") {
        if (!MusicPlayer.started) {
            println("\nStarting music server. This might take a few seconds...")
            schedule(0.1) {
                MusicPlayer.startAsNeeded()
                btn.setEnabled(false)
                stopMusicServerButton.setEnabled(true)
            }
        }
    }
    btn.setEnabled(!MusicPlayer.started)
    btn
}

val stopMusicServerButton = {
    lazy val btn: Button = Button("Srv Dn") {
        if (MusicPlayer.serverOwner) {
            if (MusicPlayer.started) {
                stopMusic()
                MusicPlayer.stop()
                btn.setEnabled(false)
                startMusicServerButton.setEnabled(true)
            }
        }
        else {
            println("Not stopping Music Server as we did not start it.")
        }
    }
    btn.setEnabled(MusicPlayer.started)
    btn
}

val stopButton = Button("Stop Playing") {
    stopMusic()
}

def updateMusicServerControls() {
    updateServerControls()
}

def updateServerControls() {
    val running = MusicPlayer.queryServerStatus
    startMusicServerButton.setEnabled(!running)
    stopMusicServerButton.setEnabled(running)
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

def showMusicServerControls() {
    showServerControls()
}

def showServerControls() {
    val cb = canvasBounds
    draw(controls.withTranslation(cb.x + 20, cb.y + 20))
}
