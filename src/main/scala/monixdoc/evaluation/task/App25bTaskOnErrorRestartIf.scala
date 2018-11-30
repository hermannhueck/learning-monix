package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.util.Random

object App25bTaskOnErrorRestartIf extends App {

  println("\n-----")

  val source = Task(Random.nextInt).flatMap {
    case even if even % 2 == 0 =>
      Task.now(even)
    case other =>
      Task.raiseError(new IllegalStateException(other.toString))
  }

  // Will keep retrying for as long as the source fails
  // with an IllegalStateException
  val randomEven: Task[Int] = source.onErrorRestartIf {
    case _: IllegalStateException => true
    case _ => false
  }

  randomEven runAsync println

  //Thread.sleep(1000L)
  println("-----\n")
}
