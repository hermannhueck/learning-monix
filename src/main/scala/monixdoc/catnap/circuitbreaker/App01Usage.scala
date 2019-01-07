package monixdoc.catnap.circuitbreaker

import monix.catnap.CircuitBreaker
import monix.eval.Task
import monix.execution.Scheduler

import scala.concurrent.duration._

object App01Usage extends App {

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

  val protectedTask: Task[Int] = for {
    cb <- circuitBreaker
    r  <- cb.protect(problematic)
  } yield r

  implicit lazy val scheduler: Scheduler = Scheduler.global

  protectedTask runAsync println

  println("-----\n")
}
