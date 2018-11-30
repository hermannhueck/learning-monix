package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.CancelableFuture
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object App03BlockingWithFuture extends App {

  println("\n-----")

  val task: Task[String] = Task.eval("Hello!").executeAsync // executeAsync introduces an async boundary

  // In order to block on a result, you have to first convert it into a Future by means of runToFuture and then you can block on it:
  val future: CancelableFuture[String] = task.runToFuture

  println(Await.result(future, 3.seconds))
  //=> Hello!

  // Or by using foreach
  val completed: CancelableFuture[String] = for (r <- future) yield "Completed!"

  println(Await.result(completed, 3.seconds))
  //=> Hello!
  //=> Completed!

  Thread.sleep(1000L)
  println("-----\n")
}
