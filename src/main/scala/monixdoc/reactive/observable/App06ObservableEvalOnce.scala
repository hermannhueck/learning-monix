package monixdoc.reactive.observable

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App06ObservableEvalOnce extends App {

  println("\n-----")

  val obs: Observable[String] = Observable.evalOnce { println("Effect"); "Hello!" }
  // obs: monix.reactive.Observable[String] = monix.reactive.internal.builders.EvalOnceObservable@6a4475c0
  val task: Task[Unit] = obs.foreachL(println)
  // task: monix.eval.Task[Unit] = Task.Async$889744615

  task runAsync { _ => () }
  //=> Effect
  //=> Hello

  // Result was memoized on the first run!
  task runAsync { _ => () }
  //=> Hello

  Thread.sleep(500L)
  println("-----\n")
}
