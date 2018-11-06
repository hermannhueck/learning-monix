package monixdoc.execution.cancelable

import monix.execution.Cancelable
import monix.execution.Scheduler.{global => scheduler}

object App01BuildingACancelable extends App {

  println("\n-----")

  // A cancelable that doesn't do anything on cancel
  val c1 = Cancelable.empty
  c1.cancel()

  // Same thing, a cancelable that doesn't do anything
  val c2 = Cancelable()
  c2.cancel()

  // Specify a callback to be called on cancel
  val c3 = Cancelable(() => println("Canceled!"))
  c3.cancel()

  println("-----\n")
}
