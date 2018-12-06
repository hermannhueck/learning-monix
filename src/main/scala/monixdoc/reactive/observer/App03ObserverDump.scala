package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
// Back-pressure related acknowledgement
import monix.reactive.{Observable, Observer}

object App03ObserverDump extends App {

  println("\n-----")

  // Observer that only logs the events that it receives
  val out = Observer.dump("O")

  Observable(1, 2, 3).subscribe(out)
  //=> O --> 1
  //=> O --> 2
  //=> O --> 3
  //=> O completed

  out.onNext(1)
  //=> 0: O --> 1
  // res0: Ack = Continue

  out.onNext(2)
  //=> 1: O --> 2
  // res0: Ack = Continue

  out.onComplete()
  //=> 2: O completed

  Thread.sleep(500L)
  println("-----\n")
}
