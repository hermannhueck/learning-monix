package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.TimeoutException
import scala.concurrent.duration._

object App05oSimpleBuildersTaskNever extends App {

  println("\n-----")

  // A Task instance that never completes
  val never = Task.never[Int]

  val timedOut = never.timeoutTo(3.seconds,
    Task.raiseError(new TimeoutException))

  timedOut.runAsync(r => println(r))
  // After 3 seconds:
  // => Failure(java.util.concurrent.TimeoutException)

  Thread.sleep(4000L)
  println("-----\n")
}
