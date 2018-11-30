package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.language.postfixOps

object App16aTaskDelayExecution extends App {

  println("\n-----")

  val source = Task {
    println("Side-effect!")
    "Hello, world!"
  }

  val delayed: Task[String] = source.delayExecution(2.seconds)
  delayed.runToFuture.foreach(println)

  Thread.sleep(3000L)
  println("-----\n")
}
