package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._
import scala.language.postfixOps

object App21cSignalingErrors extends App {

  // ----- Error in the Task.create-callback

  println("\n-----")

  val task = Task.create[Int] { (scheduler, callback) =>
    (throw new IllegalStateException("FTW!")): Unit
  }

  val future = task.runToFuture

  // Logs the following to System.err:
  /*
  java.lang.IllegalStateException: FTW!
    at monixdoc.evaluation.task.App21cSignalingErrors$.$anonfun$task$1(App21cSignalingErrors.scala:16)
    at monixdoc.evaluation.task.App21cSignalingErrors$.$anonfun$task$1$adapted(App21cSignalingErrors.scala:15)
    at monix.eval.internal.TaskCreate$.$anonfun$async0$1(TaskCreate.scala:101)
    at monix.eval.internal.TaskCreate$.$anonfun$async0$1$adapted(TaskCreate.scala:98)
    at monix.eval.internal.TaskRestartCallback.start(TaskRestartCallback.scala:58)
    at monix.eval.internal.TaskRunLoop$.executeAsyncTask(TaskRunLoop.scala:564)
    at monix.eval.internal.TaskRunLoop$.goAsync4Future(TaskRunLoop.scala:615)
    at monix.eval.internal.TaskRunLoop$.startFuture(TaskRunLoop.scala:511)
    at monix.eval.Task.runToFutureOpt(Task.scala:580)
    at monix.eval.Task.runToFuture(Task.scala:539)
    . . .
	  */

  // The Future NEVER COMPLETES, OOPS!
  future.onComplete(r => println(r))

  Thread.sleep(1000L)
  println("-----\n")
}
