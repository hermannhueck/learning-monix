package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App14aConsumerLoadBalance extends App {

  println("\n-----")

  val sumConsumer = Consumer.foldLeft[Long, Long](0L)(_ + _)

  val parallelConsumer = Consumer.loadBalance(parallelism = 10, sumConsumer).map(_.sum)

  Observable.range(0, 10000)
    .consumeWith(parallelConsumer)
    .runToFuture
    .foreach(r => println(s"Result: $r"))

  //=> Result: 49995000

  Thread.sleep(500L)
  println("-----\n")
}
