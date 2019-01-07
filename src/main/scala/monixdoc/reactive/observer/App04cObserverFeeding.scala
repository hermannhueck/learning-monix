package monixdoc.reactive.observer
// Back-pressure related acknowledgement
import monix.reactive.Observer

object App04cObserverFeeding extends App {

  println("\n-----")

  val observer = Observer.dump("0")

  observer.onNext(1)
  observer.onNext(2)    // ILLEGAL! Violates the protocol!
  observer.onComplete()

  Thread sleep 500L
  println("-----\n")
}
