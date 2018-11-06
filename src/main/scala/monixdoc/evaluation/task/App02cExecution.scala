package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object App02cExecution extends App {

  println("\n-----")

  val task = Task { println("Effect!"); "Result" }

  task.foreach { result => println(result) }
  //=> Effect!
  //=> Result

  // Or we can use for-comprehensions
  for (result <- task) {
    println(result)
  }

  Thread.sleep(1000L)
  println("-----\n")
}
