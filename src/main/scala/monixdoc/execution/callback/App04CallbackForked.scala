package monixdoc.execution.callback

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

object App04CallbackForked extends App {

  println("\n-----")

  // Lets pretend we have something meaningful
  val ref = Callback.empty[Throwable, String]

  val asyncCallback: Callback[Throwable, String] = Callback.forked(ref)

  val task: Task[String] = Task { println("side effect"); "result" }
  // task: monix.eval.Task[String] = Task.Eval$609283153

  task.runAsync(asyncCallback)
  //=> side effect
  // res2: monix.execution.Cancelable = monix.execution.Cancelable.empty

  Thread.sleep(100L)
  println("-----\n")
}
