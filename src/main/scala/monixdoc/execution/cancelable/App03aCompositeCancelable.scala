package monixdoc.execution.cancelable

import monix.execution.Cancelable
import monix.execution.cancelables.CompositeCancelable

object App03aCompositeCancelable extends App {

  println("\n-----")

  val c = CompositeCancelable()

  c += Cancelable(() => println("Canceled #1"))
  c += Cancelable(() => println("Canceled #2"))
  c += Cancelable(() => println("Canceled #3"))

  // Cancelling will trigger all 3 of them
  c.cancel()

  // Appending a new cancelable to it after cancel
  // will trigger its cancelation immediately
  c += Cancelable(() => println("Canceled #4"))
  // => Canceled #4
  
  println("-----\n")
}
