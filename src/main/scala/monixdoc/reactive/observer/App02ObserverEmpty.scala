package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
// Back-pressure related acknowledgement
import monix.execution.Ack
import monix.execution.Ack.Continue
import monix.reactive.{Observable, Observer}

import scala.concurrent.Future

object App02ObserverEmpty extends App {

  println("\n-----")

  // Observer.empty only logs errors
  val observer = Observer.empty[Int]

  Observable(1, 2, 3).subscribe(observer)

  Thread.sleep(500L)
  println("-----\n")
}
