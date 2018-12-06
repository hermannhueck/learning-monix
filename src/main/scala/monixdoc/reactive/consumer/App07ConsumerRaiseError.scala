package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App07ConsumerRaiseError extends App {

  println("\n-----")

  Observable.range(0, 4).dump("O")
    .consumeWith(Consumer.raiseError(new RuntimeException("Don't know how!")))
    .runToFuture
    .foreach(_ => println("Consumer completed"))

  //=> 0: O --> 0
  //=> 2: O stopped
  //=> 3: O canceled

  Thread.sleep(500L)
  println("-----\n")
}
