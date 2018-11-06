package monixdoc.evaluation.task

// In order to evaluate tasks, we'll need a Scheduler
import monix.execution.Scheduler.Implicits.global

// A Future type that is also Cancelable
import monix.execution.CancelableFuture

// Task is in monix.eval
import monix.eval.Task
import scala.util.{Success, Failure}

object App01Introduction extends App {

  println("\n-----")

  // Executing a sum, which (due to the semantics of apply)
  // will happen on another thread. Nothing happens on building
  // this instance though, this expression is pure, being
  // just a spec! Task by default has lazy behavior ;-)
  val task = Task { 1 + 1 }

/* runOnComplete is deprecated
  // Tasks get evaluated only on runAsync!
  // Callback style:
  val cancelable1 = task.runOnComplete { // deprecated
    case Success(value) =>
      println(value)
    case Failure(ex) =>
      System.out.println(s"ERROR: ${ex.getMessage}")
  }
  //=> 2
*/

  // Tasks get evaluated only on runAsync!
  // Callback style:
  val cancelable2 = task.runAsync { // replaces runOnComplete
    case Right(value) =>
      println(value)
    case Left(ex) =>
      System.out.println(s"ERROR: ${ex.getMessage}")
  }
  //=> 2

  // Or you can convert it into a Future
  val future: CancelableFuture[Int] = task.runToFuture // runAsync is deprecated

  // Printing the result asynchronously
  future.foreach(println)
  //=> 2

  Thread.sleep(1000L)

  println("-----\n")
}
