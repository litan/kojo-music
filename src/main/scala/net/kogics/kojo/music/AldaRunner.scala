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
import java.io.File
import scala.sys.process.Process

object AldaRunner {
  val port = 46023
  @volatile var aldaProcess: Process = _

  val homeDir = System.getProperty("user.home")

  val javaHome = System.getProperty("java.home")
  val javaDir = javaHome + "/bin"

  val aldaDir = homeDir + "/.kojo/extension/kojo-music/alda-player/bin"

  val origPath = System.getenv("PATH")

  val aldaEnv = Seq(
    ("PATH" -> s"$javaDir:$origPath"),
    ("JAVA_HOME" -> javaHome)
  )

  def isWin = {
    val os = System.getProperty("os.name").toLowerCase()
    os.startsWith("windows")
  }

  val aldaCmd =
    if (isWin) s"$aldaDir/alda-player.bat" else s"$aldaDir/alda-player"

  def runServerIfNeeded(): Unit = {
    if (aldaProcess != null) {
      return
    }

    val aldaDirFile = new File(aldaDir)
    println("Starting Alda Player...")
    val player = Seq(
      aldaCmd,
//      "-v",
      "run",
      "-p",
      s"$port"
    )
    aldaProcess = Process(player, Some(aldaDirFile), aldaEnv: _*).run()
    Runtime
      .getRuntime()
      .addShutdownHook(new Thread {
        override def run(): Unit = {
          onKojoShutdown()
        }
      })
  }

  def onStopServer(): Unit = {
    if (aldaProcess != null) {
      aldaProcess = null
    }
  }

  def onKojoShutdown(): Unit = {
    MusicPlayer.stop()
    Thread.sleep(300)
//    if (aldaProcess != null) {
//      aldaProcess.destroy()
//    }
  }
}
