package monixdoc.reactive.observer

import monix.execution.Scheduler.{global => scheduler}
import monix.reactive.observers.Subscriber
import monix.reactive.{Observable, Observer}

object App05SubscriberFromObserver extends App {

  println("\n-----")

  // Observer that only logs the events that it receives
  val observer = Observer.dump("O")
  val subscriber = Subscriber(observer, scheduler)

  Observable(1, 2, 3).subscribe(subscriber)
  //=> O --> 1
  //=> O --> 2
  //=> O --> 3
  //=> O completed

  Thread.sleep(500L)
  println("-----\n")
}
