package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App04aObservablePure extends App {

  println("\n-----")

  val obs = Observable.pure { println("Effect"); "Hello!" }   // alias for Observable.now
  //=> Effect
  // obs: monix.reactive.Observable[String] = monix.reactive.internal.builders.NowObservable@18fbc3db

  obs.subscribe(printObserver)
  //=> Hello
  //=> Completed successfully

  Thread.sleep(500L)
  println("-----\n")
}
