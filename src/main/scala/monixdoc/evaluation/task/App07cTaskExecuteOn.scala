package monixdoc.evaluation.task

import monix.eval.Task
// The default scheduler
import monix.execution.Scheduler.Implicits.global

import monix.execution.Scheduler

object App07cTaskExecuteOn extends App {

  println("\n-----")

  // Creating a special scheduler meant for I/O
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

  //=> Running on thread: run-main-2a
  //=> Running on thread: my-io-803
  //=> Ends on thread: scala-execution-context-global-800

  Thread.sleep(1000L)
  println("-----\n")
}
