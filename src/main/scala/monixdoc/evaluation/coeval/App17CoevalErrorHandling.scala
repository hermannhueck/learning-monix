package monixdoc.evaluation.coeval

import monix.eval.Coeval

import scala.util.Random

object App17CoevalErrorHandling extends App {

  println("\n-----")

  val coeval = Coeval(Random.nextInt).flatMap {
    case even if even % 2 == 0 =>
      Coeval.now(even)
    case odd =>
      throw new IllegalStateException(odd.toString)
  }

  println(coeval.runTry)
  println(coeval.runTry)
  println(coeval.runTry)
  println(coeval.runTry)
  println(coeval.runTry)
  println(coeval.runTry)
  println(coeval.runTry)

  println("-----\n")
}
