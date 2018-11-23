package monixdoc.evaluation.coeval

import monix.eval.Coeval
import monix.eval.Coeval.Eager

object App02CoevalEager extends App {

  println("\n-----")

  val coeval1 = Coeval(1 + 1)
  // coeval1: monix.eval.Coeval[Int] = Coeval.Always$996183354

  val result1 = coeval1.run
  // result1: monix.eval.Coeval.Eager[Int] = Coeval.Now(2)
  println(result1)

  val coeval2 = Coeval.raiseError[Int](new RuntimeException("Hello!"))
  // coeval2: monix.eval.Coeval[Int] = Coeval.Error(java.lang.RuntimeException: Hello!)

  val result2: Eager[Int] = coeval2.run
  // result2: monix.eval.Coeval.Eager[Int] = Coeval.Error(java.lang.RuntimeException: Hello!)
  println(result2)

  println("-----\n")
}
