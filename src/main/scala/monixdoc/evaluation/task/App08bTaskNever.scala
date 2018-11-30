package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.TimeoutException
import scala.concurrent.duration._

object App08bTaskNever extends App {

  println("\n-----")

  // A Task instance that never completes
  val never = Task.never[Int]
  // never: monix.eval.Task[Int] = Task.Async$1100817253

  val timedOut = never.timeoutTo(3.seconds, Task.raiseError(new TimeoutException))
  // timedOut: monix.eval.Task[Int] = Task.FlatMap$1111838291

  timedOut.runAsync(r => println(r))
  // After 3 seconds:
  // => Left(java.util.concurrent.TimeoutException)

  Thread.sleep(4000L)
  println("-----\n")
}
