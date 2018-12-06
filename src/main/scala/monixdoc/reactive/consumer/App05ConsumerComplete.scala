package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App05ConsumerComplete extends App {

  println("\n-----")

  Observable.range(0, 4).dump("O")
    .consumeWith(Consumer.complete)
    .runToFuture
    .foreach(_ => println("Consumer completed"))

  //=> 0: O --> 0
  //=> 1: O --> 1
  //=> 2: O --> 2
  //=> 3: O --> 3
  //=> 4: O completed
  //=> Consumer completed

  Thread.sleep(500L)
  println("-----\n")
}
