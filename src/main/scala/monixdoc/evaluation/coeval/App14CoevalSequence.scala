package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App14CoevalSequence extends App {

  println("\n-----")

  val ca = Coeval(1)
  val cb = Coeval(2)

  val list: Coeval[Seq[Int]] =
    Coeval.sequence(Seq(ca, cb))

  println(list.value)
  //=> List(1, 2)

  println("-----\n")
}
