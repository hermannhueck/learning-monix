package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App06CoevalEvalOnce extends App {

  println("\n-----")

  val coeval = Coeval.evalOnce { println("Effect"); "Hello!" }
  // coeval: monix.eval.Coeval[String] = Coeval.Suspend$758758170
  println(coeval)

  println(coeval.value)
  //=> Effect
  //=> Hello!

  // Result was memoized on the first run!
  println(coeval.value)
  //=> Hello!

  println("-----\n")
}
