package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App10bObservableOnErrorHandleWith extends App {

  println("\n-----")

  val obs: Observable[Int] = Observable(1, 2) ++ Observable.raiseError(new Exception) ++ Observable(0)

  val obs2: Observable[Int] = obs.onErrorHandleWith(_ => Observable(3, 4)) // alias for handleErrorWith

  obs2
    .foreachL(println)
    .runAsync(_ => ())
  //=> 1
  //=> 2
  //=> 3
  //=> 4

  obs2 subscribe printObserver
  //=> 1
  //=> 2
  //=> 3
  //=> 4
  //=> Completed successfully

  Thread.sleep(500L)
  println("-----\n")
}
