package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

object App12aWithoutParallel extends App {

  println("\n-----")

  val locationTask: Task[String] = Task.eval("here")
  val phoneTask: Task[String] = Task.eval("rrriiinggggggg")
  val addressTask: Task[String] = Task.eval("NoWhereLand")

  // Ordered operations based on flatMap ...
  val aggregate: Task[String] = for {
    location <- locationTask
    phone <- phoneTask
    address <- addressTask
  } yield {
    s"Gotcha!  $location-$phone-$address"
  }

  aggregate.runAsync(new Callback[Throwable, String] {
    override def onSuccess(value: String): Unit = println(value)
    override def onError(e: Throwable): Unit = println(e.toString)
  })

  // Thread.sleep(1000L)
  println("-----\n")
}
