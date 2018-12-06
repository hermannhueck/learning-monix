package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.observers.Subscriber

object App08SubscriberToReactive extends App {

  println("\n-----")

  val monixSubscriber = Subscriber.dump("O")

  val reactiveSubscriber: org.reactivestreams.Subscriber[Any] =
    monixSubscriber.toReactive

  reactiveSubscriber.onSubscribe(

    new org.reactivestreams.Subscription {

      private var isCanceled = false

      def request(n: Long): Unit = {
        if (n > 0 && !isCanceled) {
          isCanceled = true
          reactiveSubscriber.onNext(1)
          reactiveSubscriber.onComplete()
        }
      }

      def cancel(): Unit =
        isCanceled = true
    })
  //=> O --> 1
  //=> O completed

  Thread.sleep(500L)
  println("-----\n")
}
