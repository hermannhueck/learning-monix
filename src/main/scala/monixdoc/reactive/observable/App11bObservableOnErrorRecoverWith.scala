package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App11bObservableOnErrorRecoverWith extends App {

  println("\n-----")

  val obs: Observable[Int] = Observable(1, 2) ++ Observable.raiseError(new IllegalStateException) ++ Observable(0)

  val obs2a: Observable[Int] = obs.onErrorHandleWith {
    case ex: IllegalStateException => Observable(3, 4)
    case t: Throwable => throw t
  }
  // these are equivalent
  val obs2b: Observable[Int] = obs.onErrorRecoverWith {
    case ex: IllegalStateException => Observable(3, 4)
  }

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
