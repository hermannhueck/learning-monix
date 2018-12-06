package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App08ConsumerFoldLeft extends App {

  println("\n-----")

  val sum = Consumer.foldLeft[Long,Long](0L)(_ + _)

  Observable.range(0, 1000)
    .consumeWith(sum)
    .runToFuture
    .foreach(r => println(s"SUM: $r"))

  //=> SUM: 499500

  Thread.sleep(500L)
  println("-----\n")
}
