package monixdoc.evaluation.task

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.language.postfixOps
import scala.util.Random

object App20TaskToReactivePublisher extends App {

  println("\n-----")

  val task = Task.eval(Random.nextInt())

  val publisher: org.reactivestreams.Publisher[Int] =
    task.toReactivePublisher


  import org.reactivestreams._

  publisher.subscribe(new Subscriber[Int] {

    def onSubscribe(s: Subscription): Unit =
      s.request(Long.MaxValue)

    def onNext(e: Int): Unit =
      println(s"OnNext: $e")

    def onComplete(): Unit =
      println("OnComplete")

    def onError(ex: Throwable): Unit =
      System.err.println(s"ERROR: $ex")
  })

  // Will print:
  //=> OnNext: -228329246
  //=> OnComplete

  Thread.sleep(1000L)
  println("-----\n")
}
