package monixdoc.evaluation.task

import monix.eval.Task
// The default scheduler
import monix.execution.Scheduler.Implicits.global

import monix.execution.Scheduler

object App07bTaskExecuteOn extends App {

  println("\n-----")

  // Creating a special scheduler meant for I/O
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
