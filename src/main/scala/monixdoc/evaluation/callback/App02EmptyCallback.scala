package monixdoc.evaluation.callback

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

object App02EmptyCallback extends App {

  println("\n-----")

  val task: Task[Unit] = monix.eval.Task(println("Sample"))

  val emptyCallback: Callback[Throwable, Unit] = Callback.empty

  task.runAsync(emptyCallback)

  println("-----\n")
}
