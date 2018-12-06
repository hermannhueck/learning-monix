package monixdoc.reactive.observable

import cats.effect.concurrent.Ref
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App13bObservableDoOnNext extends App {

  println("\n-----")

  def observable(counterRef: Ref[Task, Int]): Task[Unit] =
    Observable(1, 2, 3)
      .doOnNext(e => counterRef.update(_ + e))
      .mapEval(e => counterRef.get.map(counter => println(s"elem: $e, counter: $counter")))
      .completedL

  Ref[Task].of(0)
    .flatMap(observable)
    .runAsync(_ => ())
  //=> elem: 1, counter: 1
  //=> elem: 2, counter: 3
  //=> elem: 3, counter: 6

  Thread.sleep(500L)
  println("-----\n")
}
