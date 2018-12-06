package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App09ObservableRaiseError extends App {

  println("\n-----")

  val obs: Observable[Int] = Observable.raiseError[Int](new Exception("my exception"))

  obs
    .onErrorHandle {ex => println(s"Got exception: ${ex.getMessage}"); 1}
    .foreachL(println)
    .runAsync(_ => ())
  //=> Got exception: my exception
  //=> 1

  obs subscribe printObserver
  //=> Completed with error: java.lang.Exception: my exception

  Thread.sleep(500L)
  println("-----\n")
}
