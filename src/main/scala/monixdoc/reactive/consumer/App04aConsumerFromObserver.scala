package monixdoc.reactive.consumer

import java.io.PrintStream

import monix.execution.Ack
import monix.execution.Ack.Continue
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable, Observer}

object App04aConsumerFromObserver extends App {

  println("\n-----")

  def dumpConsumer[A](prefix: String, out: PrintStream = System.out): Consumer[A, Unit] = {

    val observer = new Observer.Sync[A] {

      def onNext(elem: A): Ack = {
        out.println(s"$prefix --> $elem")
        Continue
      }

      def onComplete(): Unit =
        out.println(s"$prefix is complete")

      def onError(ex: Throwable): Unit =
        out.println(s"$prefix terminated with $ex")
    }

    Consumer.fromObserver[A] { implicit scheduler => observer }
  }

  Observable.fromIterable(0 until 5)
    .consumeWith(dumpConsumer("0"))
    .runToFuture

  //=> 0 --> 0
  //=> 0 --> 1
  //=> 0 --> 2
  //=> 0 --> 3
  //=> 0 --> 4
  //=> 0 is complete

  Thread.sleep(500L)
  println("-----\n")
}
