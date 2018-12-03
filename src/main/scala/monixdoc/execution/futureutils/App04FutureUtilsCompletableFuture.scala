package monixdoc.execution.futureutils

import java.util.concurrent.{CompletableFuture, CompletionStage}
import java.util.function
import java.util.function.BiConsumer

import monix.execution.FutureUtils
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Future

object App04FutureUtilsCompletableFuture extends App {

  println("\n-----")

  val fromJavaCompletable: CompletableFuture[String] = CompletableFuture.supplyAsync(() => "Hello World")

  val scalaFuture: Future[String] = FutureUtils.fromJavaCompletable(fromJavaCompletable)

  val toJavaCompletable = FutureUtils.toJavaCompletable(scalaFuture)

  runIt(toJavaCompletable)


  private def runIt[A](completable: CompletionStage[A]): CompletionStage[Void] = {

    val action: BiConsumer[A, Throwable] = new BiConsumer[A, Throwable] {
      override def accept(result: A, t: Throwable): Unit =
        println(if (t == null) result.toString else t.toString)
    }
    completable.whenComplete(action)

    completable.whenComplete { (result, t) =>
      println(if (t == null) result.toString else t.toString)
    }

    completable
      .thenAccept(r => println(r))
      .exceptionally(new function.Function[Throwable, Void] {
        override def apply(t: Throwable): Void = {
          println(t.toString)
          ().asInstanceOf[Void]
        }
      })

    completable
      .thenAccept(println)
      .exceptionally { println(_).asInstanceOf[Void] }
  }

  Thread.sleep(500L)
  println("-----\n")
}
