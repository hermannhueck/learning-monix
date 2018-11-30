package monixdoc.evaluation.task

import monix.eval.{Coeval, Task}
import monix.execution.CancelableFuture
import monix.execution.Scheduler.Implicits.global

object App04ImmediateExecutionWithCoeval extends App {

  println("\n-----")

  val task = Task.eval("Hello!")

  // Replaced with Task#start, since=3.0.0-RC2
  val tryingNow: Coeval[Either[CancelableFuture[String], String]] = task.coeval

  tryingNow.value match {

    case Left(future) =>
      // No luck, this Task really wants async execution
      future.foreach(r => println(s"Async: $r"))

    case Right(result) =>
      println(s"Got lucky: $result")
  }

  Thread.sleep(1000L)
  println("-----\n")
}
