package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.TimeoutException
import scala.concurrent.duration._
import scala.language.postfixOps

object App23bTaskTimeoutTo extends App {

  println("\n-----")

  val source = Task("Hello!").delayExecution(10.seconds)

  // Triggers error if the source does not
  // complete in 3 seconds after runAsync
  val timedOut = source.timeoutTo(
    3.seconds,
    Task.raiseError(new TimeoutException("That took too long!"))
  )

  timedOut.runAsync(r => println(r))
  //=> Left(java.util.concurrent.TimeoutException: That took too long!)

  Thread.sleep(4000L)
  println("-----\n")
}
