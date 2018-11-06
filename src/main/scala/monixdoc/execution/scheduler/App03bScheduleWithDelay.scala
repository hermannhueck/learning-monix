package monixdoc.execution.scheduler

import scala.concurrent.duration._

import monix.execution.Scheduler.{global => scheduler}

object App03bScheduleWithDelay extends App {

  println("\n-----")

  val c = scheduler.scheduleOnce(5.seconds) {
    println("Hello, world!")
  }

  // In case we change our mind, before time's up
  c.cancel()

  Thread.sleep(6000L)
  println("-----\n")
}
