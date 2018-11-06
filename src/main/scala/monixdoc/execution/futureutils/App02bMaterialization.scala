package monixdoc.execution.futureutils

import monix.execution.FutureUtils
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try

object App02bMaterialization extends App {

  println("\n-----")

  import FutureUtils.extensions._

  val ft: Future[Try[Int]] = Future(1).materialize

  // Hide exposed errors
  val f: Future[Int] = ft.dematerialize

  // Or as a simple function call
  val f2: Future[Int] = FutureUtils.dematerialize(ft)

  try {
    println(Await.result(f, 4.seconds))
  } catch {
    case t: Throwable => println(t.toString)
  }

  println("-----\n")
}
