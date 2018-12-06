package monixdoc.reactive.consumer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.Notification.{OnComplete, OnError, OnNext}
import monix.reactive.{Consumer, Observable}

object App12aConsumerForeach extends App {

  println("\n-----")

  val source = Observable.range(0,5)
  // source: monix.reactive.Observable[Long] = monix.reactive.internal.builders.RangeObservable@60a0a883

  val logger = Consumer.foreach[Long](x => println(s"Elem: $x"))
  // logger: monix.reactive.Consumer.Sync[Long,Unit] = <function1>

  val task = source.consumeWith(logger)
  // task: monix.eval.Task[Unit] = Task.Async$1518599509

  task.runToFuture
  //=> Elem: 0
  //=> Elem: 1
  //=> Elem: 2
  //=> Elem: 3
  //=> Elem: 4

  Thread.sleep(500L)
  println("-----\n")
}
