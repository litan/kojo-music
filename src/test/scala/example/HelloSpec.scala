package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class HelloSpec extends AnyFunSuite with Matchers {
  test("The Hello object should say hello") {
    Hello.greeting shouldEqual "hello"
  }
}
