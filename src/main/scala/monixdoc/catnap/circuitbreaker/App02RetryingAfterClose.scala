package monixdoc.catnap.circuitbreaker

import monix.catnap.CircuitBreaker
import monix.eval.Task
import monix.execution.Scheduler

import scala.concurrent.duration._

object App02RetryingAfterClose extends App {

  println("\n-----")

  val circuitBreaker: Task[CircuitBreaker[Task]] =
    CircuitBreaker[Task].of(
      maxFailures = 5,
      resetTimeout = 10.seconds
    )

  val unsafeCB: CircuitBreaker[Task] =
    CircuitBreaker[Task].unsafe(
      maxFailures = 5,
      resetTimeout = 10.seconds
    )

  val problematic: Task[Int] = Task {
    val nr = util.Random.nextInt()
    if (nr % 2 == 0) nr else
      throw new RuntimeException("dummy")
  }

  val protectedTask = circuitBreaker.flatMap { cb => cb.protect(problematic) }

  protectedTask.onErrorRestartLoop(100.millis) { (e, delay, retry) =>
    println("restarting ...")
    // Exponential back-off, but with a limit
    if (delay < 4.seconds)
      retry(delay * 2).delayExecution(delay)
    else
      Task.raiseError(e)
  }

  implicit lazy val scheduler: Scheduler = Scheduler.global

  protectedTask runAsync println

  println("-----\n")
}
