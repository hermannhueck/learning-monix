package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

object App14aTaskTraverse extends App {

  println("\n-----")

  def task(i: Int): Task[Int] = Task { println("Effect " + i); i }

  val list: Task[Seq[Int]] = Task.traverse(Seq(1, 2))(i => task(i))

  val cb = new Callback[Throwable, Seq[Int]] {
    override def onSuccess(value: Seq[Int]): Unit = println(value)
    override def onError(e: Throwable): Unit = println(e.toString)
  }

  // We always get this ordering:
  // list.runToFuture.foreach(println)
  list.runAsync(cb)
  //=> Effect1
  //=> Effect2
  //=> List(1, 2)

  // Thread.sleep(1000L)
  println("-----\n")
}
