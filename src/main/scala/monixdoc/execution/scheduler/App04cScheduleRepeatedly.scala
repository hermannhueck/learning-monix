package monixdoc.execution.scheduler

import monix.execution.Scheduler.{global => scheduler}

import scala.concurrent.duration._

object App04cScheduleRepeatedly extends App {

  println("\n-----")

  val c = scheduler.scheduleWithFixedDelay(3.seconds, 5.seconds) {
    println("Fixed delay task")
  }

  // In case we change our mind, before time's up
  c.cancel()

  Thread.sleep(6000L)
  println("-----\n")
}
