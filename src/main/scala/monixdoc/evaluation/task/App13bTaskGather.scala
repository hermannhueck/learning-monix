package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.language.postfixOps

object App13bTaskGather extends App {

  println("\n-----")

  val ta = Task { println("Effect1"); 1 }.delayExecution(1 second)
  val tb = Task { println("Effect2"); 2 }.delayExecution(1 second)

  val list: Task[Seq[Int]] = Task.gather(Seq(ta, tb))

  val cb = new Callback[Throwable, Seq[Int]] {
    override def onSuccess(value: Seq[Int]): Unit = println(value)
    override def onError(e: Throwable): Unit = println(e.toString)
  }

  // There’s potential for parallel execution:
  // Ordering of effects is not guaranteed, but the results in the List are ordered.
  // list.runToFuture.foreach(println)
  list.runAsync(cb)
  //=> Effect1
  //=> Effect2
  //=> List(1, 2)

  // list.runToFuture.foreach(println)
  list.runAsync(cb)
  //=> Effect2
  //=> Effect1
  //=> List(1, 2)

  Thread.sleep(3000L)
  println("-----\n")
}
