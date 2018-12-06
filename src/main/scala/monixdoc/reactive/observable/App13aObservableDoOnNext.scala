package monixdoc.reactive.observable

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App13aObservableDoOnNext extends App {

  println("\n-----")

  var counter = 0
  val observable: Observable[Int] = Observable(1, 2, 3)

  observable
    .doOnNext(e => Task(counter += e))
    .foreachL(e => println(s"elem: $e, counter: $counter"))
    .runAsync(_ => ())
  //=> elem: 1, counter: 1
  //=> elem: 2, counter: 3
  //=> elem: 3, counter: 6

  Thread.sleep(500L)
  println("-----\n")
}
