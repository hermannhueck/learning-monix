package monixdoc.reactive.consumer

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

object App10cConsumerHeadOption extends App {

  println("\n-----")

  val first: Task[Option[Int]] =
    Observable.empty[Int].consumeWith(Consumer.headOption)

  first.runToFuture.foreach(println)
  //=> None

  Thread.sleep(500L)
  println("-----\n")
}
