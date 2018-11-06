package monixdoc.execution.cancelable

import monix.execution.cancelables.OrderedCancelable
import monix.execution.{Cancelable, Scheduler}

import scala.concurrent.duration._

object App05bOrderedCancelable extends App {

  println("\n-----")

  def delayedExecution(cb: () => Cancelable)
                      (implicit s: Scheduler): Cancelable = {

    val ref = OrderedCancelable()
    val delay = s.scheduleOnce(5.seconds) {
      ref.orderedUpdate(cb(), 2)
    }

    // This should be the first update, but
    // if not, then it is ignored!
    ref.orderedUpdate(delay, 1)
    ref
  }

  println("-----\n")
}
