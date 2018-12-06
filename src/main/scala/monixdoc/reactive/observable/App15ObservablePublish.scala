package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

object App15ObservablePublish extends App {

  println("\n-----")

  var result = 0
  val observable = Observable(1, 2, 3).publish

  observable.foreach(e => result += e)

  // Start the streaming
  observable.connect()

  println(result)
  // result == 6

  // happens after all items are emitted so it doesn't add anything
  observable.foreach(e => result += e)

  println(result)
  // result == 6

  Thread.sleep(500L)
  println("-----\n")
}
