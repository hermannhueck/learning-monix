package monixdoc.catnap.mvar

import monix.catnap.MVar
import monix.eval.Task
import monix.execution.Scheduler

object App02AsynchronousLock extends App {

  println("\n-----")

  final class MLock(mvar: MVar[Task, Unit]) {
    def acquire: Task[Unit] =
      mvar.take

    def release: Task[Unit] =
      mvar.put(())

    def greenLight[A](fa: Task[A]): Task[A] =
      for {
        _ <- acquire
        a <- fa.doOnCancel(release)
        _ <- release
      } yield a
  }

  object MLock {
    /** Builder. */
    def apply(): Task[MLock] =
      MVar[Task].of(()).map(v => new MLock(v))
  }

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
      lock <- MLock()
      state <- MVar[Task].of(0)
      task = sum(state, (0 until 100).toList)
      r <- lock.greenLight(task)
    } yield r

  implicit val scheduler: Scheduler = Scheduler.global

  // Evaluate
  task.runToFuture.foreach(println)
  //=> 4950

  Thread sleep 200L
  println("-----\n")
}
