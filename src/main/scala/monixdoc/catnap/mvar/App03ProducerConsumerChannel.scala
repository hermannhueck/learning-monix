package monixdoc.catnap.mvar

import monix.catnap.MVar
import monix.eval.Task
import monix.execution.Scheduler

object App03ProducerConsumerChannel extends App {

  println("\n-----")

  // Signaling option, because we need to detect completion
  type Channel[A] = MVar[Task, Option[A]]

  def producer(ch: Channel[Int], list: List[Int]): Task[Unit] =
    list match {
      case Nil =>
        ch.put(None) // we are done!
      case head :: tail =>
        // next please
        ch.put(Some(head)).flatMap(_ => producer(ch, tail))
    }

  def consumer(ch: Channel[Int], sum: Long): Task[Long] =
    ch.take.flatMap {
      case Some(x) =>
        // next please
        consumer(ch, sum + x)
      case None =>
        Task.now(sum) // we are done!
    }

  val count = 100000

  val sumTask =
    for {
      channel <- MVar[Task].empty[Option[Int]]()
      producerTask = producer(channel, (0 until count).toList).executeAsync
      consumerTask = consumer(channel, 0L).executeAsync
      // Ensure they run in parallel, not really necessary, just for kicks
      sum <- Task.parMap2(producerTask, consumerTask)((_,sum) => sum)
    } yield sum

  implicit val scheduler: Scheduler = Scheduler.global

  // Evaluate
  sumTask.runToFuture.foreach(println)
  //=> 4999950000

  Thread sleep 1500L
  println("-----\n")
}
