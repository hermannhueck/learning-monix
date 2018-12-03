package monixdoc.execution.scheduler

import java.util.concurrent.TimeUnit

import monix.execution.Cancelable
import monix.execution.Scheduler.{global => scheduler}

object App04bScheduleRepeatedly extends App {

  println("\n-----")

  val cancelable: Cancelable = scheduler.scheduleWithFixedDelay(
    1, 3, TimeUnit.SECONDS,
    new Runnable {
      def run(): Unit = {
        Thread.sleep(2000) // 2 seconds
        // This accumulates the effective delay to 7 seconds
        println("Fixed delay task")
      }
    })
  println("started")

  Thread.sleep(15000L)

  // In case we change our mind, before time's up
  cancelable.cancel()
  println("canceled")

  // Thread.sleep(6000L)
  println("-----\n")
}
