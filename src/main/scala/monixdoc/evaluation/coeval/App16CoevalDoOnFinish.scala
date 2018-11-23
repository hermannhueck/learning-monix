package monixdoc.evaluation.coeval

import monix.eval.Coeval

import scala.util.Random

object App16CoevalDoOnFinish extends App {

  println("\n-----")

  val coeval = Coeval(1)

  val withFinishCb = coeval.doOnFinish {
    case None =>
      println("Was success!")
      Coeval.unit
    case Some(ex) =>
      println(s"Had failure: $ex")
      Coeval.unit
  }

  println(withFinishCb.value)
  //=> Was success!
  // res: Int = 1

  println("-----\n")
}
