package monixdoc.evaluation.task

import monix.execution.Scheduler.Implicits.global

import monix.eval.Task

import scala.concurrent.duration._
import scala.util.{Failure, Success}

object App02aExecution extends App {

  println("\n-----")

  val task = Task { 1 + 1 }.delayExecution(1.second)

  println("Task.runOnComplete is deprecated")
/*
  val cancelable = task.runOnComplete { // deprecated
    case Success(value) =>
      println(value)
    case Failure(ex) =>
      System.out.println(s"ERROR: ${ex.getMessage}")
  }

  // If we change our mind...
  cancelable.cancel()

  Thread.sleep(1000L)
*/

  println("-----\n")
}
