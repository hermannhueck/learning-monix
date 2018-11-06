package monixdoc.evaluation.task

import monix.execution.Scheduler.Implicits.global

import monix.eval.Task

import scala.concurrent.duration._

object App02bExecution extends App {

  println("\n-----")

  val task = Task { 1 + 1 }.delayExecution(1.second)

  val cancelable = task.runAsync { // replaces deprecated runOnComplete
    case Right(value) =>
      println(value)
    case Left(ex) =>
      System.out.println(s"ERROR: ${ex.getMessage}")
  }

  // If we change our mind...
  cancelable.cancel()

  Thread.sleep(1000L)

  println("-----\n")
}
