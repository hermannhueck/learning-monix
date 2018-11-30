package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Future

object App06cTaskDeferFuture extends App {

  println("\n-----")

  val task = Task.deferFuture {
    Future { println("Effect"); "Hello!" }
  }

  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!
  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
