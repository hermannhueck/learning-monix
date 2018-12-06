package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.Notification.{OnComplete, OnError, OnNext}
import monix.reactive.{Consumer, Observable}

object App11ConsumerFirstNotification extends App {

  println("\n-----")

  val observable = Observable.empty[Int]
  val task =
    observable.consumeWith(Consumer.firstNotification)

  task.runToFuture.foreach {
    case OnComplete => println("onComplete")
    case OnError(ex) => println(s"onError($ex)")
    case OnNext(elem) => println(s"onNext(elem)")
  }
  //=> onComplete

  Thread.sleep(500L)
  println("-----\n")
}
