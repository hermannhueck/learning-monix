package monixdoc.execution.cancelable

import monix.execution.cancelables.BooleanCancelable

object App02aBooleanCancelable extends App {

  println("\n-----")

  // Building an instance that's already canceled
  val c = BooleanCancelable.alreadyCanceled

  // Doesn't do anything
  c.cancel()

  // Always returns true
  println(c.isCanceled)

  println("-----\n")
}
