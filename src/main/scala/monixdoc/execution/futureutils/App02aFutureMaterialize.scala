package monixdoc.execution.futureutils

import monix.execution.FutureUtils
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try

object App02aFutureMaterialize extends App {

  println("\n-----")

  val f: Future[Int] = Future(1)

  // Expose errors
  import FutureUtils.extensions._
  val ft: Future[Try[Int]] = f.materialize

  // Or as a simple function call
  val ft2: Future[Try[Int]] = FutureUtils.materialize(f)

  try {
    println(Await.result(ft, 2.seconds))
  } catch {
    case t: Throwable => println(t.toString)
  }

  println("-----\n")
}
