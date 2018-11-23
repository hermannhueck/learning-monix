package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App04CoevalNow extends App {

  println("\n-----")

  val coeval = Coeval.now { println("Effect"); "Hello!" }
  //=> Effect
  // coeval: monix.eval.Coeval[String] = Coeval.Now(Hello!)

  println(coeval.value)

  println("-----\n")
}
