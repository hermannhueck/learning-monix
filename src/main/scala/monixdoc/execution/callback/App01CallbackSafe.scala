package monixdoc.execution.callback

import monix.eval.Task
import monix.execution.Callback

object App01CallbackSafe extends App {

  println("\n-----")

  val callback: Callback[Throwable, Int] = new Callback[Throwable, Int] {
    def onSuccess(value: Int): Unit =
      println(value)
    def onError(ex: Throwable): Unit =
      System.err.println(ex)
  }

  // We need an exception reporter, but we can just use a Scheduler
  import monix.execution.Scheduler.Implicits.global

  val safeCallback1 = Callback.safe(callback)

  // But really, we don't need a Scheduler
  import monix.execution.UncaughtExceptionReporter
  // import UncaughtExceptionReporter.{LogExceptionsToStandardErr => r} // LogExceptionsToStandardErr is deprecated
  import UncaughtExceptionReporter.{default => r}

  val safeCallback2 = Callback.safe(callback)(r)

  // NOTE: when executing Task.runToFuture(callback), the provided callback
  // is automatically wrapped in Callback.safe, so you don’t need to worry about it.

  Task(1 + 1).runToFuture foreach println

  Thread.sleep(100L)
  println("-----\n")
}
