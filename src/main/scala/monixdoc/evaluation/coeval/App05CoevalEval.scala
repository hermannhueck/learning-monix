package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App05CoevalEval extends App {

  println("\n-----")

  val coeval = Coeval.eval { println("Effect"); "Hello!" }
  // coeval: monix.eval.Coeval[String] = Coeval.Always$1281789995
  println(coeval)

  println(coeval.value)
  //=> Effect
  //=> Hello!

  // The evaluation (and thus all contained side effects)
  // gets triggered every time
  println(coeval.value)
  //=> Effect
  //=> Hello!

  println("-----\n")
}
