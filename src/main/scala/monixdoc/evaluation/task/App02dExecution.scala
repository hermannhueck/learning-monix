package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.{Callback, Cancelable}
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object App02dExecution extends App {

  println("\n-----")

  val task = Task { 1 + 1 }.delayExecution(1.second)

  val cancelable: Cancelable = task.runAsync(new Callback[Throwable, Int] {
    def onSuccess(value: Int): Unit =
      println(value)

    def onError(ex: Throwable): Unit =
      System.err.println(s"ERROR: ${ex.getMessage}")
  })
  println("Task started")

  // If we change our mind...
  cancelable.cancel()
  println("Task canceled")

  Thread.sleep(1000L)
  println("-----\n")
}
