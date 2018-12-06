package monixdoc.reactive.observable

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App05aObservableDelay extends App {

  println("\n-----")

  val obs: Observable[String] = Observable.delay { println("Effect"); "Hello!" }   // alias for Observable.eval
  // obs: monix.reactive.Observable[String] = monix.reactive.internal.builders.EvalAlwaysObservable@2c9655dd
  val task: Task[Unit] = obs.foreachL(println)
  // task: monix.eval.Task[Unit] = Task.Async$889744615

  obs.subscribe(printObserver)
  //=> Effect
  //=> Hello
  //=> Completed successfully

  task runAsync { _ => () }
  //=> Effect
  //=> Hello

  task runAsync { _ => () }
  //=> Effect
  //=> Hello

  Thread.sleep(500L)
  println("-----\n")
}
