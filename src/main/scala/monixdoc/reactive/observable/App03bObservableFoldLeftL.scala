package monixdoc.reactive.observable

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App03bObservableFoldLeftL extends App {

  println("\n-----")

  val obs: Observable[Long] =
    Observable.range(0, 1000)
      .take(100)
      .map(_ * 2)

  val task: Task[Long] = obs.foldLeftL(0L)(_ + _)

  task runAsync printCallback

  Thread.sleep(500L)
  println("-----\n")
}
