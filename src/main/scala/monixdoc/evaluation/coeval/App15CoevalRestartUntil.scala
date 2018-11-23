package monixdoc.evaluation.coeval

import monix.eval.Coeval

import scala.util.Random

object App15CoevalRestartUntil extends App {

  println("\n-----")

  val randomEven = {
    Coeval.eval(Random.nextInt())
      .restartUntil(_ % 2 == 0)
  }

  println(randomEven.value)
  //=> -2097793116
  println(randomEven.value)
  //=> 1246761488
  println(randomEven.value)
  //=> 1053678416

  println("-----\n")
}
