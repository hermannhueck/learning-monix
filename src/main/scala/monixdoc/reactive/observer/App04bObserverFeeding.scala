package monixdoc.reactive.observer

import monix.execution.Ack.{Continue, Stop}
import monix.execution.Scheduler.Implicits.global
// Back-pressure related acknowledgement
import monix.reactive.Observer

object App04bObserverFeeding extends App {

  println("\n-----")

  val observer = Observer.dump("0")

  observer.onNext(1).map {
    case Continue =>
      observer.onComplete()
      Stop
    case Stop =>
      // At this point we know the observer wants
      // to stop, so no onComplete!
      Stop
  }

  Thread sleep 500L
  println("-----\n")
}
