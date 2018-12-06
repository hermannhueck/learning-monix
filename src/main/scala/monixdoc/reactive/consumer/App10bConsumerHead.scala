package monixdoc.reactive.consumer

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App10bConsumerHead extends App {

  println("\n-----")

  Observable
    .empty[Int]
    .consumeWith(Consumer.head)
    .failed.runToFuture.foreach(println)
  //=> java.util.NoSuchElementException: head

  Thread.sleep(500L)
  println("-----\n")
}
