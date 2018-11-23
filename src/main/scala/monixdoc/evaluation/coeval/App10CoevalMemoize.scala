package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App10CoevalMemoize extends App {

  println("\n-----")

  val coeval = Coeval.eval { println("Effect"); "Hello!" }
  // coeval: monix.eval.Coeval[String] = Coeval.Always$1281789995
  println(coeval)

  val memoized = coeval.memoize
  // memoized: monix.eval.Coeval[String] = Coeval.Suspend$401715

  println(memoized.value)
  //=> Effect
  //=> Hello!

  println(memoized.value)
  //=> Hello!

  println("-----\n")
}
