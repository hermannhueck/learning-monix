package monixdoc.evaluation.task

import monix.eval.Task
// The default scheduler
import monix.execution.Scheduler.Implicits.global

// Creating a special scheduler meant for I/O
import monix.execution.Scheduler

object App05jSimpleBuildersTaskExecuteOn extends App {

  println("\n-----")

  lazy val io = Scheduler.io(name="my-io")

  // Override the default Scheduler by fork:
  val source = Task(println(s"Running on thread: ${Thread.currentThread.getName}"))
  val forked = source.executeOn(io)

  source.runToFuture
  //=> Running on thread: ForkJoinPool-1-worker-1
  forked.runToFuture
  //=> Running on thread: my-io-4

  Thread.sleep(1000L)
  println("-----\n")
}
