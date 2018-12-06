package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App12bConsumerForeachParallel extends App {

  println("\n-----")

  val source = Observable.range(0,100)

  val logger = Consumer.foreachParallel[Long](parallelism = 10)(x => println(s"Elem: $x"))

  val task = source.consumeWith(logger)

  task.runToFuture

  Thread.sleep(500L)
  println("-----\n")
}
