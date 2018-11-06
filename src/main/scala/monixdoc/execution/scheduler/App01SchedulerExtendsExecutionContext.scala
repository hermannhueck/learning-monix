package monixdoc.execution.scheduler

import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Future

object App01SchedulerExtendsExecutionContext extends App {

  println("\n-----")

  Future(1 + 1).foreach(println)

  Thread.sleep(100L)
  println("-----\n")
}
