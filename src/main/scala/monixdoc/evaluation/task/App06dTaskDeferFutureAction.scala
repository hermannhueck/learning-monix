package monixdoc.evaluation.task

import monix.eval.Task
//import monix.execution.Scheduler.Implicits.global

import scala.concurrent.{ExecutionContext, Future}

object App06dTaskDeferFutureAction extends App {

  println("\n-----")

  def sumFuture(list: Seq[Int])(implicit ec: ExecutionContext): Future[Int] =
    Future(list.sum)

  // with implicit ExecutionContext
  {
    import monix.execution.Scheduler.Implicits.global // acts as ExecutionContext, as Scheduler is derived from ExecutionContext

    def sumTask(list: Seq[Int])(implicit ec: ExecutionContext): Task[Int] = // implicit ExecutionContext required to define the task
      Task.deferFuture(sumFuture(list))

    sumTask(List(1, 2, 3)).runToFuture.foreach(println)
    sumTask(List(1, 2, 3)).foreach(println)
  }

  // without implicit ExecutionContext
  {
    def sumTask(list: Seq[Int]): Task[Int] =
    Task.deferFutureAction { implicit scheduler =>
      sumFuture(list)
    }

    import monix.execution.Scheduler.Implicits.global

    sumTask(List(1, 2, 3)).runToFuture.foreach(println) // implicit Scheduler required only when running it
    sumTask(List(1, 2, 3)).foreach(println) // implicit Scheduler required only when running it
  }

  Thread.sleep(1000L)
  println("-----\n")
}
