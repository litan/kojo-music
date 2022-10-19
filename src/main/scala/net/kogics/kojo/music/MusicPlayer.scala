/*
 * Copyright (C) 2022 Lalit Pant <pant.lalit@gmail.com>
 *
 * The contents of this file are subject to the GNU General Public License
 * Version 3 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.gnu.org/copyleft/gpl.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 */
package net.kogics.kojo.music
import com.illposed.osc.OSCBundle
import com.illposed.osc.transport.{
  NetworkProtocol,
  OSCPortOut,
  OSCPortOutBuilder
}

import java.io.IOException
import java.net.{InetAddress, InetSocketAddress, Socket}
import java.util.concurrent.{
  Executors,
  ScheduledExecutorService,
  ScheduledFuture,
  TimeUnit
}

object MusicPlayer {
  val protocol = NetworkProtocol.TCP
  val port = AldaRunner.port

  var client: OSCPortOut = _
  var started = false

  private val serverAddress =
    new InetSocketAddress(InetAddress.getByName(null), port)

  def waitForServerUp(): Unit = {
    Thread.sleep(3000)
    var notConnected = true
    val maxTries = 20
    var currTry = 0
    while (notConnected && currTry < maxTries) {
      val cs = new Socket()
      try {
        cs.connect(serverAddress)
        notConnected = false
        println("Server is up and listening.")
        Thread.sleep(500)
      } catch {
        case ioe: IOException =>
          currTry += 1
//          println(s"Server is not up yet ($currTry).")
          if (currTry == maxTries) {
            println("Unable to connect to the server. Giving up.")
          } else {
//            println("Will re-check in a second.")
          }
          Thread.sleep(1000)
      } finally {
        try { cs.close() }
        catch { case _: Throwable => }
      }
    }

    if (notConnected) {
      println(
        "Unable to connect to the server. It is taking longer than expected to start up." +
          "\nYou can view/control the server state via the server control panel."
      )
    }
  }

  def startAsNeeded(startServerIfNeeded: Boolean = true): Unit = synchronized {
    if (started) {
      return
    }

    if (startServerIfNeeded) {
      AldaRunner.runServerIfNeeded()
      println("Launched Music Server. Waiting...")
      waitForServerUp()
    }

    client = new OSCPortOutBuilder()
      .setNetworkProtocol(protocol)
      .setRemoteSocketAddress(serverAddress)
      .build()

    keepAlive()
    started = true
  }

  def stop(): Unit = synchronized {
    if (started) {
      stopPlaying()
      stopKeepAlive()
      playHelper(OSCBundleGenerator.shutdownOSCBundle(0))
      AldaRunner.onStopServer()
      started = false
    }
  }

  private def playHelper(bundle: OSCBundle): Boolean = synchronized {
    startAsNeeded()

    try {
      client.send(bundle)
      true
    } catch {
      case e: Throwable =>
        println("Problem talking to Alda music player - " + e.getMessage)
        false
    }
  }

  private def playNowAfterClearing(score: Score): Boolean = {
    playHelper(OSCBundleGenerator.scoreToOSCBundleWithClear(score))
  }

  private[music] def playNextOnCurrentChannels(score: Score): Boolean = {
    playHelper(OSCBundleGenerator.scoreToOSCBundleNextInitChannels(score))
  }

  private def playNowOnNextChannels(score: Score): Boolean = {
    playHelper(OSCBundleGenerator.scoreToOSCBundleNowNextChannels(score))
  }

  def play(score: Score) = playNowAfterClearing(score)
  def playAlso(score: Score) = playNowOnNextChannels(score)
  def playNext(score: Score) = playNextOnCurrentChannels(score)

  private def scoreGen(score: Score) = new ScoreGenerator {
    def nextScore: Score = score
  }

  def playLoop(score: Score): Unit = {
    playLoop(scoreGen(score))
  }

  def playLoop(scoreGenerator: ScoreGenerator): Unit = synchronized {
    stopPlaying()
    loopPlaying = true
    var firstSchedule = true

    def scheduleNextRun(rate: Int): Unit = {
      val delay = if (firstSchedule) {
        firstSchedule = false
        (rate * 0.8).toInt
      } else
        rate

      loopTaskFuture = playerTimer.schedule(
        musicTask,
        delay,
        TimeUnit.MILLISECONDS
      )
    }

    lazy val musicTask = new Runnable {
      def run(): Unit = MusicPlayer.synchronized {
        if (loopPlaying) {
          val upcomingScore = scoreGenerator.nextScore
          MusicPlayer.playNextOnCurrentChannels(upcomingScore)
          scheduleNextRun(upcomingScore.durationMillis)
        }
      }
    }

    musicTask.run()
  }

  def playLiveLoop(score: Score): Unit =
    playLiveLoop(scoreGen(score))

  def playLiveLoop(scoreGenerator: ScoreGenerator): Unit = {
    startAsNeeded()
    LiveLoop.play("live_loop0", scoreGenerator)
  }

  def cancelLoopTask(): Unit = {
    if (loopTaskFuture != null) {
      loopTaskFuture.cancel(false)
      loopTaskFuture = null
    }
  }

  def stopPlaying(): Boolean = synchronized {
    loopPlaying = false
    cancelLoopTask()
    LiveLoop.stopPlaying()
    playHelper(OSCBundleGenerator.stopOSCBundle())
  }

  private def ping(): Boolean = {
    playHelper(OSCBundleGenerator.pingOSCBundle())
  }

  lazy val playerTimer: ScheduledExecutorService =
    Executors.newSingleThreadScheduledExecutor()
  var pingTaskFuture: ScheduledFuture[_] = _
  var loopTaskFuture: ScheduledFuture[_] = _
  var loopPlaying = false

  private def keepAlive(): Unit = {
    val pingTask = new Runnable {
      def run(): Unit = {
        ping()
      }
    }
    val pingRate = 3
    pingTaskFuture = playerTimer.scheduleAtFixedRate(
      pingTask,
      pingRate,
      pingRate,
      TimeUnit.MINUTES
    )
  }

  private def stopKeepAlive(): Unit = {
    pingTaskFuture.cancel(false)
    pingTaskFuture = null
  }

}
