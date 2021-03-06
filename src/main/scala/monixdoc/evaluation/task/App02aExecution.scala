package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.CancelableFuture
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object App02aExecution extends App {

  println("\n-----")

  val task = Task { 1 + 1 }.delayExecution(1.second)

  val future: CancelableFuture[Int] =
    task.runToFuture
  println("Task started")

  // If we change our mind...
  future.cancel()
  println("Task canceled")

  Thread.sleep(1000L)

  println("-----\n")
}
