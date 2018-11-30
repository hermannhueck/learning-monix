package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.util.{Failure, Success, Try}

object App26aTaskMaterialize extends App {

  println("\n-----")

  val source = Task.raiseError[Int](new IllegalStateException)

  val materialized: Task[Try[Int]] = source.materialize

  // Now we can flatMap over both success and failure:
  val recovered = materialized.flatMap {
    case Success(value) => Task.now(value)
    case Failure(_) => Task.now(0)
  }

  recovered.runToFuture.foreach(println)
  //=> 0

  Thread.sleep(1000L)
  println("-----\n")
}
