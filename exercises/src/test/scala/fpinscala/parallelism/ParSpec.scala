package fpinscala.parallelism

import java.util.concurrent.{ExecutorService, Executors}

import org.scalatest.{Matchers, WordSpec}

class ParSpec extends WordSpec with Matchers {
  import Par._
  val es: ExecutorService = Executors.newFixedThreadPool(1)

  "sequence" should {
    "transform List[Par[Int]] to Par[List[Int]]" in {
      val ps: List[Par[Int]] = List()
      val result = sequence(ps)
      result shouldBe a[Par[List[Int]]]
    }
    "return Par[List[A]] with the correct values" in {
      val ints = List(1, 2)
      val ps: List[Par[Int]] = ints.map(unit)
      val result = sequence(ps)
      Par.run(es)(result).get() should equal(ints)
    }
  }
}
