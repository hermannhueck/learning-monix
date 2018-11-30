package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random

object App25cTaskOnErrorHandleWith extends App {

  println("\n-----")

  val source = Task(Random.nextInt).flatMap {
    case even if even % 2 == 0 =>
      Task.now(even)
    case other =>
      Task.raiseError(new IllegalStateException(other.toString))
  }

  def retryBackoff[A](source: Task[A], maxRetries: Int, firstDelay: FiniteDuration): Task[A] =
    source.onErrorHandleWith {
      case ex: Exception =>
        if (maxRetries > 0) {
          println(s"Retrying ... maxRetries = $maxRetries, nextDelay = ${firstDelay * 2}")
          // Recursive call, it's OK as Monix is stack-safe
          retryBackoff(source, maxRetries - 1, firstDelay * 2).delayExecution(firstDelay)
        } else
          Task.raiseError(ex)
    }

  val randomEven: Task[Int] = retryBackoff(source, 3, 1.second)

  println(Await.result(randomEven.runToFuture, 10.seconds))

  //Thread.sleep(10000L)
  println("-----\n")
}
