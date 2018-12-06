package monixdoc.reactive.observable

import monix.eval.Task
import monix.execution.Ack
import monix.execution.Scheduler.Implicits.global
import monix.reactive._
import monix.reactive.subjects.ConcurrentSubject

object App14ConcurrentSubject extends App {

  println("\n-----")

  val subject: ConcurrentSubject[Int, Int] =
    ConcurrentSubject[Int](MulticastStrategy.replay)

  def feedItem[A](observer: Observer[A], item: A): Task[Ack] = {
    Task.deferFuture(observer.onNext(item))
  }

  def processStream[A](observable: Observable[A]): Task[Unit] = {
    observable
      .mapParallelUnordered(3)(i => Task(println(i)))
      .completedL
  }

  Task
    .parZip2(
      feedItem(subject, 2),
      processStream(subject)
    ) foreach println
  //=> 2

  Thread.sleep(500L)
  println("-----\n")
}
