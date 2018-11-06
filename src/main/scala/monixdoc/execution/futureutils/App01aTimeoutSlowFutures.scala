package monixdoc.execution.futureutils

import monix.execution.FutureUtils
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._

object App01aTimeoutSlowFutures extends App {

  println("\n-----")

  // Creating a never ending Future
  val p: Promise[Unit] = Promise[Unit]()
  val never: Future[Unit] = p.future

  // Creates a new Future that has a race condition
  // with an error signaling a `TimeoutException`
  // if the source doesn't complete in time
  import FutureUtils.extensions._
  never.timeout(3.seconds)

  // Or as a simple function call
  FutureUtils.timeout(never, 3.seconds)

  never.onComplete { try_ =>
    val resultString = try_.toEither.fold(_.toString, _ => "Success")
    println(resultString)
  }

  try {
    println(Await.result(never, 4.seconds))
  } catch {
    case t: Throwable => println(t.toString)
  }

  //Thread.sleep(4000L)

  println("-----\n")
}
