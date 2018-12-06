package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App18ObservableBehavior extends App {

  println("\n-----")

  var result = 0
  val observable = Observable(1, 2, 3).behavior(0)

  // Start the streaming
  observable.connect()

  observable.foreach(e => result += e)

  println(result)
  // result == 3

  Thread.sleep(500L)
  println("-----\n")
}
