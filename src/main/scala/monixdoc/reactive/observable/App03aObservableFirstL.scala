package monixdoc.reactive.observable

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App03aObservableFirstL extends App {

  println("\n-----")

  val obs: Observable[Long] = Observable.range(0, 1000)
  val task: Task[Long] = obs.firstL

  task runAsync printCallback

  Thread.sleep(500L)
  println("-----\n")
}
