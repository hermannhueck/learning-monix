package monixdoc.execution.cancelable

import monix.execution.Cancelable
import monix.execution.cancelables.CompositeCancelable

object App03bCompositeCancelable extends App {

  println("\n-----")

  val composite = CompositeCancelable()

  val c1 = Cancelable(() => println("Canceled #1"))
  val c2 = Cancelable(() => println("Canceled #2"))

  composite += c1
  composite += c2

  // Removing from our composite
  composite -= c1

  // Canceling will now only cancel c2
  composite.cancel()
  // => Canceled #2

  println("-----\n")
}
