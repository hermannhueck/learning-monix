package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App09CoevalUnit extends App {

  println("\n-----")

  val coeval = Coeval.unit
  // coeval: monix.eval.Coeval[Unit] = Coeval.Now(())
  println(coeval)

  println(coeval.runTry)
  //=> Success(())

  println(coeval.value)
  //=> ()

  println("-----\n")
}
