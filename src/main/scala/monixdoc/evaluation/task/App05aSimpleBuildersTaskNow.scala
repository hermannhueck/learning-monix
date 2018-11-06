package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App05aSimpleBuildersTaskNow extends App {

  println("\n-----")

  val task = Task.now { println("Effect"); "Hello!" }
  //=> Effect
  // task: monix.eval.Task[String] = Delay(Now(Hello!))

  task foreach println

  Thread.sleep(1000L)
  println("-----\n")
}
