package monixdoc.execution.scheduler

import java.util.concurrent.TimeUnit

import monix.execution.Scheduler.{global => scheduler}

object App04aScheduleRepeatedly extends App {

  println("\n-----")

  val c = scheduler.scheduleWithFixedDelay(
    3, 5, TimeUnit.SECONDS,
    new Runnable {
      def run(): Unit = {
        println("Fixed delay task")
      }
    })

  // In case we change our mind, before time's up
  c.cancel()

  Thread.sleep(6000L)
  println("-----\n")
}
