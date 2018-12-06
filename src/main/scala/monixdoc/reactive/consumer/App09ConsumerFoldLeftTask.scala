package monixdoc.reactive.consumer

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}
import concurrent.duration._

object App09ConsumerFoldLeftTask extends App {

  println("\n-----")

  val sum: Consumer[Long, Long] = Consumer.foldLeftTask[Long,Long](0L) { (acc, elem) =>
    Task(acc + elem).delayExecution(10.millis)
  }

  Observable.range(0, 100)
    .consumeWith(sum)
    .runToFuture
    .foreach(r => println(s"SUM: $r"))

  //=> SUM: 4950

  Thread.sleep(5000L)
  println("-----\n")
}
