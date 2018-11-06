package monixdoc.evaluation.task

import monix.eval.Task

import scala.concurrent.TimeoutException
// The default scheduler
import monix.execution.Scheduler.Implicits.global

object App05nSimpleBuildersTaskRaiseError extends App {

  println("\n-----")

  val error = Task.raiseError[Int](new TimeoutException)
  // error: monix.eval.Task[Int] =
  //   Delay(Error(java.util.concurrent.TimeoutException))

  error.runAsync(result => println(result))
  //=> Failure(java.util.concurrent.TimeoutException)

  Thread.sleep(1000L)
  println("-----\n")
}
