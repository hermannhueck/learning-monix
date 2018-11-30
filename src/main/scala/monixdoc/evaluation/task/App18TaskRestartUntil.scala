package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.language.postfixOps
import scala.util.Random

object App18TaskRestartUntil extends App {

  println("\n-----")

  val randomEven = {
    Task.eval(Random.nextInt())
      .restartUntil(_ % 2 == 0)
  }

  randomEven.runToFuture.foreach(println)
  //=> -2097793116
  randomEven.runToFuture.foreach(println)
  //=> 1246761488
  randomEven.runToFuture.foreach(println)
  //=> 1053678416

  Thread.sleep(1000L)
  println("-----\n")
}
