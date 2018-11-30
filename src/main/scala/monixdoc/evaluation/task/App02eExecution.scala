package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App02eExecution extends App {

  println("\n-----")

  val task = Task { println("Effect!"); "Result" }

  task.foreach { result => println(result) }
  //=> Effect!
  //=> Result

  // Or we can use for-comprehensions
  for (result <- task) {
    println(result)
  }
  //=> Effect!
  //=> Result

  Thread.sleep(1000L)
  println("-----\n")
}
