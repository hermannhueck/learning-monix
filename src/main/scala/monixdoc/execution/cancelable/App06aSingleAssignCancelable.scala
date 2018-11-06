package monixdoc.execution.cancelable

import monix.execution.Scheduler.{global => scheduler}
import monix.execution.cancelables.SingleAssignCancelable

import scala.concurrent.duration._

object App06aSingleAssignCancelable extends App {

  println("\n-----")

  val ref = SingleAssignCancelable()

  ref := scheduler.scheduleAtFixedRate(0.seconds, 5.seconds) {
    // This wouldn't be correct without having an already
    // initialized value, as it would be a forward reference
    // (e.g. a reference used before it's initialized), which
    // could lead to a NullPointerException, not cool!
    ref.cancel()
  }

  println("-----\n")
}
