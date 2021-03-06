package monixdoc.reactive.observer

import monix.execution.Ack.{Continue, Stop}
import monix.execution.Scheduler.Implicits.global
// Back-pressure related acknowledgement
import monix.reactive.Observer

object App04aObserverFeeding extends App {

  println("\n-----")

  val observer = Observer.dump("0")

  observer.onNext(1)
  observer.onComplete()

  Thread sleep 500L
  println("-----\n")
}
