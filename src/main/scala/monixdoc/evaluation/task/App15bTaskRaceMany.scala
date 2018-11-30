package monixdoc.evaluation.task

import monix.eval.{Fiber, Task}
import monix.execution.CancelableFuture
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.language.postfixOps

object App15bTaskRaceMany extends App {

  println("\n-----")

  val ta = Task(1 + 1).delayExecution(1 second)
  val tb = Task(10).delayExecution(1 second)

  val winnerTask: Task[Int] = Task.raceMany(Seq(ta, tb))

  winnerTask.runToFuture
    .foreach(r => println(s"Winner: $r"))

  Thread.sleep(3000L)
  println("-----\n")
}
