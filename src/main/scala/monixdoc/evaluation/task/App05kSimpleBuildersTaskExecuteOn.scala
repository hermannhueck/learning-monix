package monixdoc.evaluation.task

import monix.eval.Task
// The default scheduler
import monix.execution.Scheduler.Implicits.global

// Creating a special scheduler meant for I/O
import monix.execution.Scheduler

object App05kSimpleBuildersTaskExecuteOn extends App {

  println("\n-----")

  lazy val io = Scheduler.io(name="my-io")

  // Override the default Scheduler by fork:
  val source = Task(println(s"Running on thread: ${Thread.currentThread.getName}"))
  val forked = source.executeOn(io)

  val onFinish = Task.eval(
    println(s"Ends on thread: ${Thread.currentThread.getName}")
  )

  val cancelable = {
    source.flatMap(_ => forked)
      .doOnFinish(_ => onFinish)
      .runToFuture
  }

  //=> Running on thread: ForkJoinPool-1-worker-7
  //=> Running on thread: my-io-1
  //=> Ends on thread: my-io-1 // scala-execution-context-global-2585

  Thread.sleep(1000L)
  println("-----\n")
}
