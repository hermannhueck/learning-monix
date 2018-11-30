package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.language.postfixOps
import scala.util.Random

object App21aSignalingErrors extends App {

  // ----- Error in the task definition

  println("\n-----")

  val task = Task(Random.nextInt).flatMap {
    case even if even % 2 == 0 =>
      Task.now(even)
    case odd =>
      throw new IllegalStateException(odd.toString)
  }

  task.runAsync(r => println(r))
  //=> Left(-924040280)

  task.runAsync(r => println(r))
  //=> Right(java.lang.IllegalStateException: 834919637)

  Thread.sleep(1000L)
  println("-----\n")
}
