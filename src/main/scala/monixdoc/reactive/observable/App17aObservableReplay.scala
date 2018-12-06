package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App17aObservableReplay extends App {

  println("\n-----")

  var result = 0
  val observable = Observable(1, 2, 3).replay

  // Start the streaming
  observable.connect()

  observable.foreach(e => result += e)

  println(result)
  // result == 6

  Thread.sleep(500L)
  println("-----\n")
}
