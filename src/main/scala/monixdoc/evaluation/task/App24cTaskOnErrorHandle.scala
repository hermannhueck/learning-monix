package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.TimeoutException
import scala.concurrent.duration._
import scala.language.postfixOps

object App24cTaskOnErrorHandle extends App {

  println("\n-----")

  val source = {
    Task("Hello!")
      .delayExecution(10.seconds)
      .timeout(3.seconds)
  }

  val recovered = source.onErrorHandle {
    case _: TimeoutException =>
      // Oh, we know about timeouts, recover it
      "Recovered!"
    case other =>
      throw other // Rethrowing
  }

  recovered.runToFuture.foreach(println)
  //=> Recovered!

  Thread.sleep(4000L)
  println("-----\n")
}
