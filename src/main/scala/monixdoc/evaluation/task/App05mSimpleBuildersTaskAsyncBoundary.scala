package monixdoc.evaluation.task

import monix.eval.Task
// The default scheduler
import monix.execution.Scheduler.Implicits.global

// Creating a special scheduler meant for I/O
import monix.execution.Scheduler

object App05mSimpleBuildersTaskAsyncBoundary extends App {

  println("\n-----")

  lazy val io = Scheduler.io(name="my-io")

  // Override the default Scheduler by fork:
  val source = Task(println(s"Running on thread: ${Thread.currentThread.getName}"))
  val forked = source.executeOn(io)

  val asyncBoundary = Task.unit.executeAsync
  val onFinish = Task.eval(
    println(s"Ends on thread: ${Thread.currentThread.getName}"))

  val cancelable = {
    source // executes on global
      .flatMap(_ => forked) // executes on io
      .asyncBoundary // switch back to global
      .doOnFinish(_ => onFinish) // executes on global
      .runToFuture
  }

  //=> Running on thread: ForkJoinPool-1-worker-5
  //=> Running on thread: my-io-2
  //=> Ends on thread: ForkJoinPool-1-worker-5

  Thread.sleep(1000L)
  println("-----\n")
}
