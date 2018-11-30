package monixdoc.evaluation.task

import monix.execution.Scheduler.Implicits.global
import monix.eval.Task
import monix.execution.Cancelable

import scala.concurrent.duration._
import scala.util.{Failure, Success}

object App02bExecution extends App {

  println("\n-----")

  val task = Task { 1 + 1 }.delayExecution(1.second)

  println("Task.runOnComplete is deprecated")
  val cancelable: Cancelable = task.runOnComplete { // deprecated
    case Success(value) =>
      println(value)
    case Failure(ex) =>
      System.out.println(s"ERROR: ${ex.getMessage}")
  }
  println("Task started")

  // If we change our mind...
  cancelable.cancel()
  println("Task canceled")

  Thread.sleep(1000L)

  println("-----\n")
}
