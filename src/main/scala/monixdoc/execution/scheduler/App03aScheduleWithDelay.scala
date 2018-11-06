package monixdoc.execution.scheduler

import java.util.concurrent.TimeUnit

import monix.execution.Scheduler.{global => scheduler}

object App03aScheduleWithDelay extends App {

  println("\n-----")

  val cancelable = scheduler.scheduleOnce(
    5, TimeUnit.SECONDS,
    new Runnable {
      def run(): Unit = {
        println("Hello, world!")
      }
    })

  // In case we change our mind, before time's up
  cancelable.cancel()

  Thread.sleep(6000L)
  println("-----\n")
}
