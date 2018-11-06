package monixdoc.execution.scheduler

import monix.execution.Scheduler
import monix.execution.schedulers.SchedulerService
import monix.execution.Scheduler.global

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object App07SchedulerShutdown extends App {

  println("\n-----")

  val io: SchedulerService = Scheduler.io("my-io")

  io.execute(new Runnable {
    def run(): Unit = {
      println("Hello, world!")
    }
  })

  io.shutdown()
  println(io.isShutdown)

  val termination: Future[Boolean] =
    io.awaitTermination(30.seconds, global)

  Await.result(termination, Duration.Inf)

  println(io.isTerminated)

  println("-----\n")
}
