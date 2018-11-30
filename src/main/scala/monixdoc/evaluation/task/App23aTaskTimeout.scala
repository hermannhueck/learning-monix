package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.language.postfixOps

object App23aTaskTimeout extends App {

  println("\n-----")

  val source = Task("Hello!").delayExecution(10.seconds)

  // Triggers error if the source does not
  // complete in 3 seconds after runAsync
  val timedOut = source.timeout(3.seconds)

  timedOut.runAsync(r => println(r))
  //=> Left(java.util.concurrent.TimeoutException: Task timed-out after 3 seconds of inactivity)

  Thread.sleep(4000L)
  println("-----\n")
}
