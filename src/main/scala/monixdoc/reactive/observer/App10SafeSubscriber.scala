package monixdoc.reactive.observer

import monix.execution.{Ack, Scheduler}
import monix.execution.Scheduler.Implicits.global
import monix.reactive.observers.{SafeSubscriber, Subscriber}

object App10SafeSubscriber extends App {

  println("\n-----")

  def createSafeSubsciber[A]: SafeSubscriber[A] =
    SafeSubscriber(new Subscriber[A] {

      val scheduler: Scheduler = global

      def onNext(elem: A): Ack =
        throw new IllegalStateException("onNext")

      def onComplete(): Unit =
        println("Completed!")

      def onError(ex: Throwable): Unit =
        System.err.println(s"Error: $ex")
    })

  val out = createSafeSubsciber[Int]
  // Error in onNext gets caught and handled
  out.onNext(1)
  //=> Error: java.lang.IllegalStateException: onNext
  // res: Future[Ack] = Stop

  // Repeating it will have no further effect
  out.onNext(10)
  // res: Future[Ack] = Stop

  val out2 = createSafeSubsciber[Int]
  out2.onComplete()
  //=> Completed!

  // No further events are accepted
  out2.onNext(1)
  // res: Future[Ack] = Stop

  // Note that when subscribing to observables, if you use the regular subscribe methods
  // (and not unsafeSubscribeFn) the callbacks you give are automatically wrapped
  // in a SafeSubscriber, so you don’t have to do it by yourself.

  println("-----\n")
}
