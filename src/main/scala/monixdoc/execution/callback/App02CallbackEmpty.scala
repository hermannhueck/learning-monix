package monixdoc.execution.callback

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

object App02CallbackEmpty extends App {

  println("\n-----")

  val task = Task(println("side effect"))
  // task: monix.eval.Task[Unit] = Task.Eval$609283153

  task.runAsync(Callback.empty[Throwable, Unit])
  //=> side effect
  // res2: monix.execution.Cancelable = monix.execution.Cancelable.empty

  Thread.sleep(100L)
  println("-----\n")
}
