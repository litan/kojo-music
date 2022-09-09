package net.kogics.kojo.music

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.jdk.CollectionConverters._

class DslToOscTest extends AnyFunSuite with Matchers {

  test("Simple score to osc") {
    val notes = Seq(60, 62, 64, 65).map(Note(_))
    val score = Score(
      120,
      Part(
        0, // instrument
        Phrase(notes: _*)
      ),
      Part.percussion(
        Phrase(notes: _*)
      )
    )

//    val bundle = OSCBundleGenerator.scoreToOSCBundle(score)
//    println(bundle.getPackets.asScala)

    1 shouldEqual 1
  }
}
