package monixdoc.execution.scheduler

import java.util.concurrent.TimeUnit

import monix.execution.Cancelable
import monix.execution.Scheduler.{global => scheduler}

object App04dScheduleRepeatedly extends App {

  println("\n-----")

  val cancelable: Cancelable = scheduler.scheduleAtFixedRate(
    1, 3, TimeUnit.SECONDS,
    new Runnable {
      def run(): Unit = {
        println("Fixed delay task")
      }
    })
  println("started")

  Thread.sleep(10000L)

  // In case we change our mind, before time's up
  cancelable.cancel()
  println("canceled")

  // Thread.sleep(6000L)
  println("-----\n")
}
