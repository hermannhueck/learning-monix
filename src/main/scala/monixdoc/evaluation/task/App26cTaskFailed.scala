package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Try

object App26cTaskFailed extends App {

  println("\n-----")

  val source: Task[Int] = Task.raiseError[Int](new IllegalStateException)

  val throwable: Task[Throwable] = source.failed

  throwable.runToFuture.foreach(println)
  //=> java.lang.IllegalStateException
  println(Await.result(throwable.runToFuture, 1.second))
  //=> java.lang.IllegalStateException

  Thread.sleep(500L)
  println("-----\n")
}
