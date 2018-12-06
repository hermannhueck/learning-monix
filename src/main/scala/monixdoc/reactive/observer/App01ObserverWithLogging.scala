package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
// Back-pressure related acknowledgement
import monix.execution.Ack
import monix.execution.Ack.Continue
import monix.reactive.{Observable, Observer}

import scala.concurrent.Future

object App01ObserverWithLogging extends App {

  println("\n-----")

  val observer = new Observer[Any] {

    def onNext(elem: Any): Future[Ack] = {
      println(s"O --> $elem")
      // Continue already inherits from Future[Ack],
      // so we can return it directly ;-)
      Continue
    }

    def onError(ex: Throwable): Unit =
      ex.printStackTrace()

    def onComplete(): Unit =
      println("O completed")
  }

  Observable(1, 2, 3).subscribe(observer)
  //=> O --> 1
  //=> O --> 2
  //=> O --> 3
  //=> O completed

  Thread.sleep(500L)
  println("-----\n")
}
