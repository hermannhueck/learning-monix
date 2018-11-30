package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App07aTaskExecuteAsync extends App {

  println("\n-----")

  // Task.executeAsync ensures an asynchronous boundary, forcing the fork of a (logical) thread on execution.
  val task = Task.eval("Hello!").executeAsync

  task.runToFuture.foreach(println)

  Thread.sleep(1000L)
  println("-----\n")
}
