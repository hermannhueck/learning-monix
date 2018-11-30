package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.util.Random

object App25aTaskOnErrorRestart extends App {

  println("\n-----")

  val source = Task(Random.nextInt).flatMap {
    case even if even % 2 == 0 =>
      Task.now(even)
    case other =>
      Task.raiseError(new IllegalStateException(other.toString))
  }

  // Will retry 2 times for a random even number,
  // or fail if the maxRetries is reached!
  val randomEven: Task[Int] = source.onErrorRestart(maxRetries = 2)

  randomEven runAsync println

  //Thread.sleep(1000L)
  println("-----\n")
}
