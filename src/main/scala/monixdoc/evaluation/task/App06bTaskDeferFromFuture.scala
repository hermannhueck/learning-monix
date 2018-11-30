package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Future

object App06bTaskDeferFromFuture extends App {

  println("\n-----")

  val task = Task.defer {
    val future = Future { println("Effect"); "Hello!" }
    Task.fromFuture(future)
  }
  //=> task: monix.eval.Task[Int] = Suspend(<function0>)

  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!
  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
