package monixdoc.execution.cancelable

import monix.execution.cancelables.BooleanCancelable

object App02cBooleanCancelable extends App {

  println("\n-----")

  val c = BooleanCancelable(() => println("Effect!"))

  c.cancel()

  println("-----\n")
}
