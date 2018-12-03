package monixdoc.execution.scheduler

import monix.execution.Cancelable

import scala.concurrent.duration._
import monix.execution.Scheduler.{global => scheduler}

object App03bScheduleWithDelay extends App {

  println("\n-----")

  val cancelable: Cancelable = scheduler.scheduleOnce(2.seconds) {
    println("Hello, world!")
  }
  println("started")

  // In case we change our mind, before time's up
  cancelable.cancel()
  println("canceled")

  Thread.sleep(2200L)
  println("-----\n")
}
