package monixdoc.evaluation.coeval

import monix.eval.Coeval

import scala.util.Random

object App19CoevalOnErrorRestart extends App {

  println("\n-----")

  val source = Coeval(Random.nextInt).flatMap {
    case even if even % 2 == 0 =>
      Coeval.now(even)
    case other =>
      Coeval.raiseError(new IllegalStateException(other.toString))
  }

  // Will retry 4 times for a random even number,
  // or fail if the maxRetries is reached!
  val randomEven1: Coeval[Int] = source.onErrorRestart(maxRetries = 2)
  println(randomEven1.value)

  // Will keep retrying for as long as the source fails
  // with an IllegalStateException
  val randomEven2 = source.onErrorRestartIf {
    case _: IllegalStateException => true
    case _ => false
  }
  println(randomEven2.value)

  println("-----\n")
}
