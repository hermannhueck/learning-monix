package monixdoc.reactive.consumer

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observable}

import scala.concurrent.Future
import scala.util.Random

object App13aConsumerForeachTask extends App {

  println("\n-----")

  val source = Observable.range(0,5)

  def webRequest(item: Long): Future[Long] = {
    // Play WS, or whatever ...
    // ws.clientUrl(s"https://web.com/request/$item").post()
    Future { Random.nextLong }
  }

  val consumer = Consumer.foreachTask[Long] { item => // replaces foreachAsync
    val req: Future[Long] = webRequest(item)
    Task.fromFuture(req).map(r => println(r))
  }

  val task = source.consumeWith(consumer)

  task.runToFuture
  //=> 6841582963532391313
  //=> 5950146739069445091
  //=> 1847369123198797951
  //=> 7656329611470034195
  //=> 1794813502679248731

  Thread.sleep(500L)
  println("-----\n")
}
