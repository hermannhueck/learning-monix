package monixdoc.reactive.consumer

import java.io.PrintStream

import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable, Observer}

object App04bConsumerFromObserver extends App {

  println("\n-----")

  def dumpConsumer[A](prefix: String, out: PrintStream = System.out): Consumer[A, Unit] = {

    val observer = Observer.dump(prefix, out)

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
