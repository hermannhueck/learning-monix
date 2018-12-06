package monixdoc.reactive.consumer

import monix.execution.Ack.Continue
import monix.execution.Scheduler.Implicits.global
import monix.execution.cancelables.AssignableCancelable
import monix.execution.{Ack, Callback, Scheduler}
import monix.reactive.observers.Subscriber
import monix.reactive.{Consumer, Observable}

object App02ConsumerImplementInterface extends App {

  println("\n-----")

  val sumConsumer: Consumer[Int,Long] =
    new Consumer[Int,Long] {
      def createSubscriber(cb: Callback[Throwable, Long], s: Scheduler): (Subscriber[Int], AssignableCancelable) = {

        val out: Subscriber.Sync[Int] = new Subscriber.Sync[Int] {

          implicit val scheduler: Scheduler = s
          private var sum = 0L

          def onNext(elem: Int): Ack = {
            sum += elem
            Continue
          }

          def onComplete(): Unit = {
            // We are done so we can signal the final result
            cb.onSuccess(sum)
          }

          def onError(ex: Throwable): Unit = {
            // Error happened, so we signal the error
            cb.onError(ex)
          }
        }

        // Returning a tuple of our subscriber and a dummy
        // AssignableCancelable because we don't intend to use it
        (out, AssignableCancelable.dummy)
      }
    }

  Observable.fromIterable(0 until 10000)
    .consumeWith(sumConsumer)
    .runToFuture
    .foreach(r => println(s"Result: $r"))

  //=> Result: 49995000

  Thread.sleep(500L)
  println("-----\n")
}
