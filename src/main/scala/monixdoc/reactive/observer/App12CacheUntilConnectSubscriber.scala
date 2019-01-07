package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
import monix.execution.{Ack, CancelableFuture}
import monix.reactive.observers.{CacheUntilConnectSubscriber, Subscriber}

object App12CacheUntilConnectSubscriber extends App {

  println("\n-----")

  val underlying = Subscriber.dump("O")
  val subscriber = CacheUntilConnectSubscriber(underlying)

  // Gets cached in an underlying buffer
  // to be streamed after connect
  subscriber.onNext(10)
  // res0: Future[Ack] = Continue
  subscriber.onNext(20)
  // res1: Future[Ack] = Continue
  subscriber.onNext(30)
  // res2: Future[Ack] = Continue

  // Nothing happens until connect
  val result: CancelableFuture[Ack] = subscriber.connect()
  //=> 0: O --> 10
  //=> 1: O --> 20
  //=> 2: O --> 30

  Thread sleep 500L
  println("-----\n")
}
