package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App07ObservableFromIterable extends App {

  println("\n-----")

  val obs = Observable.fromIterable(List(1, 2, 3))
  // obs: monix.reactive.Observable[Int] = IterableAsObservable@7b0e123d

  obs.foreachL(println) runAsync { _ => () }
  //=> 1
  //=> 2
  //=> 3

  Thread.sleep(500L)
  println("-----\n")
}
