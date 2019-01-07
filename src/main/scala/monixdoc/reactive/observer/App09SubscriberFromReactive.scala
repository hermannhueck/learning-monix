package monixdoc.reactive.observer

import monix.execution.Cancelable
import monix.execution.Scheduler.Implicits.global
import monix.reactive.observers.Subscriber
import org.reactivestreams
import org.reactivestreams.{Subscription => RSubscription}

object App09SubscriberFromReactive extends App {

  println("\n-----")

  val reactiveSubscriber: reactivestreams.Subscriber[Int] =
    new org.reactivestreams.Subscriber[Int] {

      private var s: RSubscription = _

      def onSubscribe(s: RSubscription): Unit = {
        this.s = s
        s.request(1)
      }

      def onNext(elem: Int): Unit = {
        println(s"O --> $elem")
        s.request(1)
      }

      def onComplete(): Unit =
        println("O completed")

      def onError(ex: Throwable): Unit =
        ex.printStackTrace()
    }

  val monixSubscriber =
    Subscriber.fromReactiveSubscriber(
      reactiveSubscriber,
      Cancelable(() => println("Was canceled!"))
    )

  monixSubscriber.onNext(1)
  //=> O --> 1
  monixSubscriber.onNext(2)
  //=> O --> 2
  monixSubscriber.onComplete()
  //=> O completed

  Thread sleep 500L
  println("-----\n")
}
