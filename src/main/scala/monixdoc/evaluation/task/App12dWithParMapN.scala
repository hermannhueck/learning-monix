package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

object App12dWithParMapN extends App {

  println("\n-----")

  val locationTask: Task[String] = Task.eval("here")
  val phoneTask: Task[String] = Task.eval("rrriiinggggggg")
  val addressTask: Task[String] = Task.eval("NoWhereLand")

  import cats.syntax.parallel._

  // Potentially executed in parallel
  val aggregate: Task[String] =
    (locationTask, phoneTask, addressTask) parMapN {
      (location, phone, address) => s"Gotcha!  $location-$phone-$address"
    }

  aggregate.runAsync(new Callback[Throwable, String] {
    override def onSuccess(value: String): Unit = println(value)
    override def onError(e: Throwable): Unit = println(e.toString)
  })

  // Thread.sleep(1000L)
  println("-----\n")
}
