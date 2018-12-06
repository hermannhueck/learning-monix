package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App11cObservableOnErrorFallbackTo extends App {

  println("\n-----")

  val obs: Observable[Int] = Observable(1, 2) ++ Observable.raiseError(new Exception) ++ Observable(0)

  val obs2a: Observable[Int] = obs.onErrorHandleWith(_ => Observable(3, 4))
  // these are equivalent
  val obs2b: Observable[Int] = obs.onErrorFallbackTo(Observable(3, 4))

  obs2b
    .foreachL(println)
    .runAsync(_ => ())
  //=> 1
  //=> 2
  //=> 3
  //=> 4

  obs2a subscribe printObserver
  //=> 1
  //=> 2
  //=> 3
  //=> 4
  //=> Completed successfully

  Thread.sleep(500L)
  println("-----\n")
}
