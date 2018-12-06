package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable
import monix.reactive.observers.Subscriber

object App06SubscriberEmpty extends App {

  println("\n-----")

  // Subscriber.empty only logs errors
  val subscriber = Subscriber.empty[Int]

  Observable(1, 2, 3).subscribe(subscriber)

  Thread.sleep(500L)
  println("-----\n")
}
