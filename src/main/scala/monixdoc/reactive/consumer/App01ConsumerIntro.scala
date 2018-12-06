package monixdoc.reactive.consumer

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Consumer
import monix.reactive.{Observable, Observer}

object App01ConsumerIntro extends App {

  println("\n-----")

  // A consumer that folds over the elements of the stream,
  // producing a sum as a result
  val sumConsumer: Consumer[Long, Long] = Consumer.foldLeft[Long, Long](0L)(_ + _)

  // For processing sums in parallel, useless of course, but can become
  // really helpful for logic sprinkled with I/O bound stuff
  val loadBalancer: Consumer[Long, Long] = {
    Consumer
      .loadBalance(parallelism = 10, sumConsumer)
      .map(_.sum)
  }

  val observable: Observable[Long] = Observable.range(0, 100000)

  // Our consumer turns our observable into a Task processing sums, w00t!
  val task: Task[Long] = observable.consumeWith(loadBalancer)

  // Consume the whole stream and get the result
  task.runToFuture.foreach(println)
  //=> 4999950000

  Thread.sleep(500L)
  println("-----\n")
}
