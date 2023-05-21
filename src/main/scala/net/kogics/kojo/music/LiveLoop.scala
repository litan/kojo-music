package net.kogics.kojo.music

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.Calendar

object LiveLoop {
  var debugLog = false // can be changed from a running Kojo instance
  import MusicPlayer.playerTimer
  val scoreGen = new ConcurrentHashMap[String, ScoreGenerator]()
  val scoreTask = new ConcurrentHashMap[String, ScheduledFuture[_]]()

  private[music] def stopPlaying(): Unit = {
    scoreGen.clear()
    scoreTask.values().forEach { t =>
      t.cancel(false)
    }
  }

  private def loopPlaying(name: String) = scoreGen.containsKey(name)
  private def scoreGenerator(name: String) = scoreGen.get(name)

  private def logDebug(msg: => String): Unit = {
    if (debugLog) {
      val calendar = Calendar.getInstance()
      val prefix =
        s"${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}" +
          s":${calendar.get(Calendar.SECOND)}:${calendar.get(Calendar.MILLISECOND)}"
      println(s"$prefix - $msg")
    }
  }

  private[music] def play(name: String, scoreGenerator0: ScoreGenerator): Unit =
    synchronized {
      if (loopPlaying(name)) {
        scoreGen.put(name, scoreGenerator0)
        logDebug("Updated live-loop.")
        return
      }

      scoreGen.put(name, scoreGenerator0)
      var firstSchedule = true
      val latencyDelta = 150 // ms

      def scheduleNextRun(rate: Int): Unit = {
        val delay = if (firstSchedule) {
          firstSchedule = false
          if (rate < 2 * latencyDelta) {
            (rate * 0.8).toInt
          }
          else {
            rate - latencyDelta
          }
        }
        else
          rate

        logDebug(s"Scheduling next run with delay - $delay ms.")
        val loopTaskFuture = playerTimer.schedule(
          musicTask,
          delay,
          TimeUnit.MILLISECONDS
        )
        scoreTask.put(name, loopTaskFuture)
      }

      lazy val musicTask = new Runnable {
        def run(): Unit = MusicPlayer.synchronized {
          if (loopPlaying(name)) {
            val upcomingScore = scoreGenerator(name).nextScore
            MusicPlayer.playNextOnCurrentChannels(upcomingScore)
            scheduleNextRun(upcomingScore.durationMillis)
          }
        }
      }

      musicTask.run()
    }
}
