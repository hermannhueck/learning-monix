package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.util.Try

object App10aTaskMemoize extends App {

  println("\n-----")

  // Has async execution, to do the .apply semantics
  val task = Task { println("Effect"); "Hello!" }

  val memoized = task.memoize // compare with evalOnce

  memoized.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  memoized.runToFuture.foreach(println)
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
