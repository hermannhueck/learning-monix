package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App05cSimpleBuildersTaskEvalOnce extends App {

  println("\n-----")

  // this operation is effectively Task.eval(f).memoize.
  val task = Task.evalOnce { println("Effect"); "Hello!" }
  // task: monix.eval.Task[String] = EvalOnce(<function0>)

  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  // Result was memoized on the first run!
  task.runToFuture.foreach(println)
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
