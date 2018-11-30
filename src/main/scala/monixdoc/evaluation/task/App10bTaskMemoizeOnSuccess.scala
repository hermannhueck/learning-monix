package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

import scala.language.postfixOps

object App10bTaskMemoizeOnSuccess extends App {

  println("\n-----")

  var effect = 0

  val source = Task.eval {
    effect += 1
    if (effect < 3) throw new RuntimeException("dummy") else effect
  }

  val cached = source.memoizeOnSuccess

  val f1 = cached.runToFuture // yields RuntimeException
  val f2 = cached.runToFuture // yields RuntimeException
  val f3 = cached.runToFuture // yields 3
  val f4 = cached.runToFuture // yields 3

  printFutureResult(f1)
  printFutureResult(f2)
  printFutureResult(f3)
  printFutureResult(f4)

  def printFutureResult(f: Future[Int]): Unit =
    try {
      println(Await.result(f, 3 seconds))
    } catch {
      case t: Throwable =>
        println(t.toString)
    }

  Thread.sleep(1000L)
  println("-----\n")
}
