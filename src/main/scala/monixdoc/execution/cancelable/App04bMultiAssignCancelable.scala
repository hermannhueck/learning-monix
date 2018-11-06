package monixdoc.execution.cancelable

import monix.execution.{Cancelable, _}
import monix.execution.cancelables.MultiAssignCancelable

import scala.concurrent.duration._

object App04bMultiAssignCancelable extends App {

  println("\n-----")

  // INCORRECT EXAMPLE
  // fixed in App05bOrderedCancelable

  def delayedExecution(cb: () => Cancelable)(implicit s: Scheduler): Cancelable = {

    val ref = MultiAssignCancelable()

    ref := s.scheduleOnce(5.seconds) {
      ref := cb()
    }

    ref
  }

  println("-----\n")
}
