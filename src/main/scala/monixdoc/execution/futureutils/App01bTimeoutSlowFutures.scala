package monixdoc.execution.futureutils

import monix.execution.FutureUtils
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise, TimeoutException}

object App01bTimeoutSlowFutures extends App {

  println("\n-----")
  
  // Creating a never ending Future
  val p: Promise[Unit] = Promise[Unit]()
  val never: Future[Unit] = p.future

  // After 3 seconds of inactivity, discards the
  // source and fallbacks to the backup
  import FutureUtils.extensions._
  never.timeoutTo(3.seconds, Future.failed(new TimeoutException))

  // Or as a simple function call
  FutureUtils.timeoutTo(never, 3.seconds,
    Future.failed(new TimeoutException))

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
