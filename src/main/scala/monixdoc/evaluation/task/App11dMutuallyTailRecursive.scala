package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App11dMutuallyTailRecursive extends App {

  println("\n-----")

  def odd(n: Int): Task[Boolean] =
    Task.eval(n == 0).flatMap {
      case true => Task.now(false)
      case false => even(n - 1)
    }

  def even(n: Int): Task[Boolean] =
    Task.eval(n == 0).flatMap {
      case true => Task.now(true)
      case false => odd(n - 1)
    }

  val task = even(1000000)

  task foreach println

  Thread.sleep(1000L)
  println("-----\n")
}
