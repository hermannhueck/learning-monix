package monixdoc.catnap.circuitbreaker

import monix.catnap.CircuitBreaker
import monix.eval.Task
import monix.execution.Scheduler

import scala.concurrent.duration._

object App03AwaitClose extends App {

  println("\n-----")

  val circuitBreaker: CircuitBreaker[Task] =
    CircuitBreaker[Task].unsafe(
      maxFailures = 5,
      resetTimeout = 10.seconds
    )

  val problematic: Task[Int] = Task {
    val nr = util.Random.nextInt()
    if (nr % 2 == 0) nr else
      throw new RuntimeException("dummy")
  }

  val protectedTask = circuitBreaker.protect(problematic)

  protectedTask.onErrorRestartLoop(0) { (e, times, retry) =>
    println("restarting ...")
    // Retrying for a maximum of 10 times
    if (times < 10)
      circuitBreaker.awaitClose.flatMap(_ => retry(times + 1))
    else
      Task.raiseError(e)
  }

  implicit lazy val scheduler: Scheduler = Scheduler.global

  protectedTask runAsync println

  println("-----\n")
}
