package monixdoc.evaluation.task

import monix.eval.{Fiber, Task}
import monix.execution.{Callback, CancelableFuture}
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.language.postfixOps

object App15aTaskRacePair extends App {

  println("\n-----")

  val ta = Task(1 + 1).delayExecution(1 second)
  val tb = Task(10).delayExecution(1 second)

  val raceTask: Task[Either[(Int, Fiber[Int]), (Fiber[Int], Int)]] = Task.racePair(ta, tb)

  val raceFuture: CancelableFuture[Either[(Int, Fiber[Int]), (Fiber[Int], Int)]] = raceTask.runToFuture

  /*
  raceFuture.foreach {
    case Left((a, fiber)) =>
      fiber.cancel.flatMap { _ =>
        Task.eval(println(s"A succeeded: $a"))
      }
    case Right((fiber, b)) =>
      fiber.cancel.flatMap { _ =>
        Task.eval(println(s"B succeeded: $b"))
      }
  }
  */

  raceFuture.foreach {
    case Left((a, fiber)) =>
      fiber.cancel
      println(s"A succeeded: $a")
    case Right((fiber, b)) =>
      fiber.cancel
      println(s"B succeeded: $b")
  }

  Thread.sleep(3000L)
  println("-----\n")
}
