package monixdoc.evaluation.task

import monix.execution.Scheduler.Implicits.global
import monix.eval.Task
import monix.execution.Cancelable

import scala.concurrent.duration._

object App02cExecution extends App {

  println("\n-----")

  val task = Task { 1 + 1 }.delayExecution(1.second)

  val cancelable: Cancelable = task.runAsync { // replaces deprecated runOnComplete
    case Right(value) =>
      println(value)
    case Left(ex) =>
      System.out.println(s"ERROR: ${ex.getMessage}")
  }
  println("Task started")

  // If we change our mind...
  cancelable.cancel()
  println("Task canceled")

  Thread.sleep(1000L)

  println("-----\n")
}
