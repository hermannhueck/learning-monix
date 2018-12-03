package monixdoc.execution.callback

import monix.eval.Task
import monix.execution.{Callback, Cancelable}
import monix.execution.Scheduler.Implicits.global

object App03CallbackFromPromise extends App {

  println("\n-----")

  val p = scala.concurrent.Promise[String]()
  // p: scala.concurrent.Promise[String] = Future(<not completed>)

  val callback = Callback.fromPromise(p)
  // callback: monix.execution.Callback[Throwable,String] = <function1>

  val task: Task[String] = Task { println("side effect"); "result" }
  // task: monix.eval.Task[Unit] = Task.Eval$609283153

  val c: Cancelable = task.runAsync(callback)
  //=> side effect
  // c: monix.execution.Cancelable = monix.execution.Cancelable.empty

  Thread.sleep(100L)
  println("-----\n")
}
