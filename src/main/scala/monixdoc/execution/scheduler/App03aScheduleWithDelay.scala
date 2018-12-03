package monixdoc.execution.scheduler

import java.util.concurrent.TimeUnit

import monix.execution.Cancelable
import monix.execution.Scheduler.{global => scheduler}

object App03aScheduleWithDelay extends App {

  println("\n-----")

  val cancelable: Cancelable = scheduler.scheduleOnce(
    2, TimeUnit.SECONDS,
    new Runnable {
      def run(): Unit = {
        println("Hello, world!")
      }
    })
  println("started")

  // In case we change our mind, before time's up
  cancelable.cancel()
  println("canceled")

  Thread.sleep(2200L)
  println("-----\n")
}
