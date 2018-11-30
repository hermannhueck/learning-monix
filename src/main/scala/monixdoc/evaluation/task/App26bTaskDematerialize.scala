package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Try

object App26bTaskDematerialize extends App {

  println("\n-----")

  val source: Task[Int] = Task.raiseError[Int](new IllegalStateException)

  // Exposing errors
  val materialized: Task[Try[Int]] = source.materialize

  println(Await.result(materialized.runToFuture, 1.second))
  println("-----")

  // Hiding errors again
  val dematerialized: Task[Int] = materialized.dematerialize

  println(Await.result(dematerialized.runToFuture, 1.second))

  //Thread.sleep(1000L)
  println("-----\n")
}
