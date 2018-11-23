package monixdoc.evaluation.callback

import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

object App02EmptyCallback extends App {

  println("\n-----")

  val task = monix.eval.Task(println("Sample"))

  task.runAsync(Callback.empty)

  println("-----\n")
}
