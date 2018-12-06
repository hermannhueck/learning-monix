package monixdoc.reactive.consumer

import monix.execution.Ack
import monix.execution.Ack.Continue
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable, Observer}

object App03ConsumerCreate extends App {

  println("\n-----")

  val sumConsumer: Consumer[Int,Long] =
    Consumer.create[Int,Long] { (scheduler, cancelable, callback) =>
      new Observer.Sync[Int] {
        private var sum = 0L

        def onNext(elem: Int): Ack = {
          sum += elem
          Continue
        }

        def onComplete(): Unit = {
          // We are done so we can signal the final result
          callback.onSuccess(sum)
        }

        def onError(ex: Throwable): Unit = {
          // Error happened, so we signal the error
          callback.onError(ex)
        }
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
