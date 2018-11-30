package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.TimeoutException
import scala.concurrent.duration._

object App08cTaskUnit extends App {

  println("\n-----")

  // Task.unit is returning an already completed Task[Unit] instance, provided as a utility,
  // to spare you creating new instances with Task.now(()):

  val task = Task.unit
  // task: monix.eval.Task[Unit] = Task.Now(())

  task.runAsync(r => println(r))
  // => Right(())

  Thread.sleep(4000L)
  println("-----\n")
}
