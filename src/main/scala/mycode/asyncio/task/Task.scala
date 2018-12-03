package mycode.asyncio

import java.util.concurrent.TimeUnit

import monix.execution.Scheduler

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

/*
case class Callback[A]() extends (Either[Throwable, A] => Unit) {
  def apply(): Unit = {
    case Left(t: Throwable) => throw t
    case Right(a) => a
  }
}
*/

object Task {

  type Callback[A] = Either[Throwable, A] => Unit

  def now[A](a: A): Task[A] = Task { () => a }

  def pure[A](a: A): Task[A] = now(a)

  def raiseError[A](t: => Throwable): Task[A] = Task[A] { () => throw t }

  def eval[A](a: => A): Task[A] = suspend(a)

  def suspend[A](a: => A): Task[A] = Task { () => a }
}

case class Task[A](run: () => A) { self =>

  import Task._

  def map[B](f: A => B): Task[B] = Task { () => f(run()) }

  def flatMap[B](f: A => Task[B]): Task[B] = f(run())

  def runToFuture(implicit ec: ExecutionContext): Future[A] = Future { run() }

  def runOnComplete(cb: Try[A] => Unit)(implicit ec: ExecutionContext): Unit = {
    ec.execute(new Runnable {
      override def run(): Unit = cb(Try {
        self.run()
      })
    })
  }

  def runAsync(cb: Callback[A])(implicit scheduler: Scheduler): Unit = {
    scheduler.scheduleOnce(0L, TimeUnit.SECONDS, new Runnable {
      override def run(): Unit = cb(Try {
        self.run()
      }.toEither)
    })
  }
}
