package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App16ObservablePublishLast extends App {

  println("\n-----")

  var result = 0
  val observable = Observable(1, 2, 3).publishLast

  observable.foreach(e => result += e)

  // Start the streaming
  observable.connect()

  println(result)
  // result == 3

  Thread.sleep(500L)
  println("-----\n")
}
