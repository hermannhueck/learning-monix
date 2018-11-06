package monixdoc.execution.cancelable

import monix.execution.Cancelable
import monix.execution.cancelables.SingleAssignCancelable

object App06bSingleAssignCancelable extends App {

  println("\n-----")

  val c = {
    val guest = Cancelable(() => println("extra canceled"))
    SingleAssignCancelable.plusOne(guest)
  }

  c := Cancelable(() => println("primary canceled"))

  c.cancel()
  //=> extra canceled
  //=> primary canceled

  println("-----\n")
}
