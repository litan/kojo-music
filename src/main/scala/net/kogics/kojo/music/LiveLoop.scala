package net.kogics.kojo.music

import java.util.concurrent.{ConcurrentHashMap, ScheduledFuture, TimeUnit}

object LiveLoop {
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

  private[music] def play(name: String, scoreGenerator0: ScoreGenerator): Unit =
    synchronized {
      if (loopPlaying(name)) {
        scoreGen.put(name, scoreGenerator0)
        return
      }

      scoreGen.put(name, scoreGenerator0)
      var firstSchedule = true
      val safeLatencyDelta = 900 // ms

      def scheduleNextRun(rate: Int): Unit = {
        val delay = if (firstSchedule) {
          firstSchedule = false
          math.max((rate * 0.8).toInt, rate - safeLatencyDelta)
        } else
          rate

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
