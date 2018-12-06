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
      // We have permission to continue
      observer.onNext(2)
      // No back-pressure required here
      observer.onComplete()
      Stop
    case Stop =>
      // Nothing else to do
      Stop
  }

  Thread.sleep(500L)
  println("-----\n")
}
