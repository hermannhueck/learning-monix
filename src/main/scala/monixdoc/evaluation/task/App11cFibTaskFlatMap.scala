package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object App11cFibTaskFlatMap extends App {

  println("\n-----")

  def fib(cycles: Int, a: BigInt = 0, b: BigInt = 1): Task[BigInt] =
    Task.eval(cycles > 0).flatMap {
      case true =>
        fib(cycles-1, b, a+b)
      case false =>
        Task.now(b)
    }

  val task = fib(5)

  task foreach println

  Thread.sleep(1000L)
  println("-----\n")
}
