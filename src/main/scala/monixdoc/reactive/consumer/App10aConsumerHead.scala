package monixdoc.reactive.consumer

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App10aConsumerHead extends App {

  println("\n-----")

  val sum: Task[Long] =
    Observable
      .range(0,1000)
      .sum
      .consumeWith(Consumer.head)

  sum.runToFuture.foreach(println)
  //=> 499500

  Thread.sleep(500L)
  println("-----\n")
}
