package monixdoc.reactive.observable

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App02ObservableConsumeWith extends App {

  println("\n-----")

  val list: Observable[Long] =
    Observable.range(0, 1000)
      .take(100)
      .map(_ * 2)

  val consumer: Consumer[Long, Long] =
    Consumer.foldLeft(0L)(_ + _)

  val task: Task[Long] =
    list.consumeWith(consumer)

  task runAsync printCallback

  Thread.sleep(500L)
  println("-----\n")
}
