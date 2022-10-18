package net.kogics.kojo.music

object Util {
  lazy val mathx = Class.forName("net.kogics.kojo.kmath.Kmath")
  lazy val mathx_lcm =
    mathx.getDeclaredMethod("lcm", classOf[Int], classOf[Int])

  def lcm(n1: Int, n2: Int): Int = {
    mathx_lcm.invoke(null, n1, n2).asInstanceOf[Int]
  }
}
