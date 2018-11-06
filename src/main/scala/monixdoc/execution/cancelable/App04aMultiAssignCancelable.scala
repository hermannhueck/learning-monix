package monixdoc.execution.cancelable

import monix.execution.Cancelable
import monix.execution.cancelables.MultiAssignCancelable

object App04aMultiAssignCancelable extends App {

  println("\n-----")

  val multiAssignment = MultiAssignCancelable()

  val c1 = Cancelable(() => println("Canceled #1"))
  multiAssignment := c1

  val c2 = Cancelable(() => println("Canceled #2"))
  multiAssignment := c2

  // Canceling it will only cancel the last assignee
  multiAssignment.cancel()
  // => Cancelled #2

  // Subsequent assignments are canceled immediately
  val c3 = Cancelable(() => println("Canceled #3"))
  multiAssignment := c3
  // => Canceled #3

  println("-----\n")
}
