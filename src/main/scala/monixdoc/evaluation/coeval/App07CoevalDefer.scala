package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App07CoevalDefer extends App {

  println("\n-----")

  // For example this will behave approximately like Coeval.eval:
  val coeval = Coeval.defer {
    Coeval.now { println("Effect"); "Hello!" }
  }
  // coeval: monix.eval.Coeval[String] = Coeval.Suspend$1664731177
  println(coeval)

  println(coeval.value)
  //=> Effect
  //=> Hello!

  println(coeval.value)
  //=> Effect
  //=> Hello!

  println("-----\n")
}
