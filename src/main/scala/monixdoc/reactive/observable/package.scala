package monixdoc.reactive

import monix.execution.{Ack, Callback}
import monix.reactive.Observer

import scala.concurrent.Future

package object observable {

  def printCallback[A]: Callback[Throwable, A] = new Callback[Throwable, A] {
    override def onSuccess(value: A): Unit = println(value)
    override def onError(e: Throwable): Unit = println(e.toString)
  }

  def printObserver[A]: Observer[A] = new Observer[A] {

    override def onNext(elem: A): Future[Ack] = {
      println(elem)
      Ack.Continue
    }

    override def onError(ex: Throwable): Unit =
      println(">>> Completed with error: " + ex.toString)

    override def onComplete(): Unit =
      println(">>> Completed successfully")
  }
}
