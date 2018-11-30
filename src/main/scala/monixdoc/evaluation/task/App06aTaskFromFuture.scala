package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Future

object App06aTaskFromFuture extends App {

  println("\n-----")

  val future = Future { println("Effect"); "Hello!" }
  val task = Task.fromFuture(future)
  //=> Effect

  task.runToFuture.foreach(println)
  //=> Hello!
  task.runToFuture.foreach(println)
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
