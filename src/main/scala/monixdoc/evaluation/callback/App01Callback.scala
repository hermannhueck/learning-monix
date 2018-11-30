package monixdoc.evaluation.callback

import monix.execution.Callback

object App01Callback extends App {

  println("\n-----")

  println("Creating a Callback.")

  val callback = new Callback[Throwable, Int] {
    def onSuccess(value: Int): Unit =
      println(value)
    def onError(ex: Throwable): Unit =
      System.err.println(ex)
  }

  println("Turning it into a safe Callback.")
  println("A safe Callback cannot be invoked twice.")

  // We need an exception reporter, but we can just use a Scheduler
  import monix.execution.Scheduler.Implicits.global

  val safeCallback1 = Callback.safe(callback) // provide ExceptionReporter implicitly

  // But really, we don't need a Scheduler
  import monix.execution.UncaughtExceptionReporter
  //import UncaughtExceptionReporter.{LogExceptionsToStandardErr => r} // deprecated
  import UncaughtExceptionReporter.{default => reporter}

  val safeCallback2 = Callback.safe(callback)(reporter) // provide ExceptionReporter explicitly

  println("-----\n")
}
