package monixdoc.catnap.mvar

import monix.execution.{CancelableFuture, Scheduler}
import monix.catnap.MVar
import monix.eval.Task

object App01SynchronizedMutableVariable extends App {

  println("\n-----")

  def sum(state: MVar[Task, Int], list: List[Int]): Task[Int] =
    list match {
      case Nil => state.take
      case x :: xs =>
        state.take.flatMap { current =>
          state.put(current + x).flatMap(_ => sum(state, xs))
        }
    }

  val task =
    for {
      state <- MVar[Task].of(0)
      r <- sum(state, (0 until 100).toList)
    } yield r

  implicit val scheduler: Scheduler = Scheduler.global

  // Evaluate
  task.runToFuture.foreach(println)
  //=> 4950

  Thread sleep 200L
  println("-----\n")
}
