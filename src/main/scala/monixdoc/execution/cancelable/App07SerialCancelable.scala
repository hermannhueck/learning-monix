package monixdoc.execution.cancelable

import monix.execution.Cancelable
import monix.execution.cancelables.SerialCancelable

object App07SerialCancelable extends App {

  println("\n-----")

  val ref = SerialCancelable()

  ref := Cancelable(() => println("Canceled #1"))

  // Will cancel the previous one
  ref := Cancelable(() => println("Canceled #2"))
  // => Canceled #1

  // Will cancel the previous one
  ref := Cancelable(() => println("Canceled #3"))
  // => Canceled #2

  // Will cancel the current one
  ref.cancel()
  // => Canceled #3

  // Afterwards everything gets canceled on assignment
  ref := Cancelable(() => println("Canceled #4"))
  // => Canceled #4

  println("-----\n")
}
