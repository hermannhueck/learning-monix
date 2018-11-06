package monixdoc.execution.cancelable

import monix.execution.cancelables.BooleanCancelable

object App02bBooleanCancelable extends App {

  println("\n-----")

  val c = BooleanCancelable()

  println(c.isCanceled)

  c.cancel()

  println(c.isCanceled)

  println("-----\n")
}
