package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App14bConsumerLoadBalance extends App {

  println("\n-----")

  val sumConsumer1 = Consumer.foldLeft[Long, Long](0L)(_ + _ + 1)

  val sumConsumer2 = Consumer.foldLeft[Long, Long](0L)(_ + _ + 2)

  val parallelConsumer =
    Consumer.loadBalance(sumConsumer1, sumConsumer2).map(_.sum)

  Thread.sleep(500L)
  println("-----\n")
}
