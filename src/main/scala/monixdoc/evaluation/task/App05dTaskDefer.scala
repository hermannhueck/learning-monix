package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App05dTaskDefer extends App {

  println("\n-----")

  // this function is also aliased as Task.suspend.
  val task = Task.defer {
    Task.now { println("Effect"); "Hello!" }
  }
  // task: monix.eval.Task[String] = Task.Suspend$1085540194

  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  // Result was memoized on the first run!
  task.runToFuture.foreach(println)
  //=> Effect
  //=> Hello!

  Thread.sleep(1000L)
  println("-----\n")
}
