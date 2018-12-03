package monixdoc.execution.scheduler

import monix.execution.Cancelable
import monix.execution.Scheduler.{global => scheduler}

import scala.concurrent.duration._

object App04eScheduleRepeatedly extends App {

  println("\n-----")

  val cancelable: Cancelable = scheduler.scheduleAtFixedRate(1.seconds, 3.seconds) {
    println("Fixed delay task")
  }
  println("started")

  Thread.sleep(10000L)

  // In case we change our mind, before time's up
  cancelable.cancel()
  println("canceled")

  // Thread.sleep(6000L)
  println("-----\n")
}
