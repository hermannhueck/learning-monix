package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App12bObservableOnErrorRestartUnlimited extends App {

  println("\n-----")

  case object TimeoutException extends Exception

  val observable = Observable(1, 2) ++ Observable.raiseError(TimeoutException) ++ Observable(0)

  observable
    .onErrorRestartUnlimited
    .foreachL(println)
    .runAsync(_ => ())
  //=> 1
  //=> 2
  //=> 1
  //=> 2
  // ... fails and restarts infinitely

  Thread.sleep(500L)
  println("-----\n")
}
