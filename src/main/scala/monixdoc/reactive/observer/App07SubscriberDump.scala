package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable
import monix.reactive.observers.Subscriber

object App07SubscriberDump extends App {

  println("\n-----")

  // Subscriber that only logs the events that it receives
  val subscriber = Subscriber.dump("O")

  Observable(1, 2, 3).subscribe(subscriber)
  //=> O --> 1
  //=> O --> 2
  //=> O --> 3
  //=> O completed

  Thread.sleep(500L)
  println("-----\n")
}
