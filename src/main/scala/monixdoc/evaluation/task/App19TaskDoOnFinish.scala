package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.language.postfixOps
import scala.util.Random

object App19TaskDoOnFinish extends App {

  println("\n-----")

  val task: Task[Int] = Task(1)

  val withFinishCb: Task[Int] = task.doOnFinish {
    case None =>
      println("Was success!")
      Task.unit
    case Some(ex) =>
      println(s"Had failure: $ex")
      Task.unit
  }

  withFinishCb.runToFuture.foreach(println)
  //=> Was success!
  //=> 1

  Thread.sleep(1000L)
  println("-----\n")
}
