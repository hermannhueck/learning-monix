package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.TimeoutException
import scala.concurrent.duration._
import scala.language.postfixOps

object App24aTaskOnErrorHandleWith extends App {

  println("\n-----")

  val source = {
    Task("Hello!")
      .delayExecution(10.seconds)
      .timeout(3.seconds)
  }

  val recovered = source.onErrorHandleWith {
    case _: TimeoutException =>
      // Oh, we know about timeouts, recover it
      Task.now("Recovered!")
    case other =>
      // We have no idea what happened, raise error!
      Task.raiseError(other)
  }

  recovered.runToFuture.foreach(println)
  //=> Recovered!

  Thread.sleep(4000L)
  println("-----\n")
}
