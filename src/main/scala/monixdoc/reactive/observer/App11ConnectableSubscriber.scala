package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
import monix.reactive.observers.{ConnectableSubscriber, Subscriber}

object App11ConnectableSubscriber extends App {

  println("\n-----")

  val underlying = Subscriber.dump("O")
  val connectable = ConnectableSubscriber(underlying)

  // Queue for delivery after connect happens and after
  // enqueued items by means of pushNext. At this point
  // the subscriber back-pressures the source with a
  // Future[Ack] that will complete only after connect()
  val ack = connectable.onNext("b1")

  // Acknowledgement not given because we are back-pressuring
  ack.isCompleted
  // res: Boolean = false

  // Queueing items to be delivered first on connect()
  connectable.pushFirst("a1")
  connectable.pushFirst("a2")

  // Nothing gets streamed until now:
  connectable.connect()
  //=> 0: O --> a1
  //=> 1: O --> a2
  //=> 2: O --> b1

  // The data-source is now no longer paused
  ack.isCompleted
  // res: Boolean = true

  Thread.sleep(500L)
  println("-----\n")
}
