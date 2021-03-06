package monixdoc.execution.futureutils

import monix.execution.FutureUtils
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try

object App03FutureUtilsDelayedResult extends App {

  println("\n-----")

  val f: Future[String] = FutureUtils.delayedResult(1.seconds) {
    "Hello, world!"
  }

  println(Await.result(f, 2.seconds))

  println("-----\n")
}
