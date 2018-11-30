package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App05cTaskEvalOnce extends App {

  println("\n-----")

  // this operation is effectively Task.eval(f).memoize.
  val task = Task.evalOnce { println("Effect"); "Hello!" }
  // task: monix.eval.Task[String] = Task.Eval$597990286

  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  // Result was memoized on the first run!
  task.runToFuture.foreach(println)
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
