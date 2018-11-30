package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App05bTaskEval extends App {

  println("\n-----")

  val task = Task.eval { println("Effect"); "Hello!" }
  // task: monix.eval.Task[String] = Task.Eval$1518077822

  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  // The evaluation (and thus all contained side effects)
  // gets triggered on each runAsync:
  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
