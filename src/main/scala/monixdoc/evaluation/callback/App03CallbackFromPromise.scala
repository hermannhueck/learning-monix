package monixdoc.evaluation.callback

import monix.execution.Callback

object App03CallbackFromPromise extends App {

  println("\n-----")

  val p = scala.concurrent.Promise[String]()

  val callback = Callback.fromPromise(p)

  println("-----\n")
}
