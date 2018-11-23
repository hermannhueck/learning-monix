package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App08CoevalRaiseError extends App {

  println("\n-----")

  val error = Coeval.raiseError[Int](new IllegalStateException)
  // error: monix.eval.Coeval[Int] = Error(java.lang.IllegalStateException)

  println(error)

  println(error.runTry)
  //=> Failure(java.lang.IllegalStateException)

  println("-----\n")
}
