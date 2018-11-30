package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.language.postfixOps

object App16bTaskDelayExecutionWith extends App {

  println("\n-----")

  val trigger = Task.unit.delayExecution(2.seconds)

  val source = Task {
    println("Side-effect!")
    "Hello, world!"
  }

  val delayed: Task[String] = source.delayExecutionWith(trigger) // deprecated: use flatMap instead
  delayed.runToFuture.foreach(println)

  Thread.sleep(3000L)
  println("-----\n")
}
