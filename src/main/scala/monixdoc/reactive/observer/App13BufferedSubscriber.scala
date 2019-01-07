package monixdoc.reactive.observer

import monix.execution.Scheduler.Implicits.global
import monix.execution.{Ack, CancelableFuture}
import monix.reactive.observers.{CacheUntilConnectSubscriber, Subscriber}

object App13BufferedSubscriber extends App {

  println("\n-----")

  val description = """
Observers have a strong contract and consequently:

- are not thread-safe
- have a back-pressure requirement

There are instances in which these requirements are limiting. A BufferedSubscriber describes
(and Monix can wrap any implementation into) a Subscriber that:

  1. has onNext, onComplete and onError methods that can be called concurrently
  2. has implementations that always have synchronous behavior, returning an immediate Continue
  3. has an onNext that returns an immediate Continue for as long as the buffer isn’t full
  4. buffers the connection between the upstream and the underlying subscriber such that the underlying subscriber can consume events at its own pace

Given that the underlying consumer can be slower than the source and given that we have a buffer between the data source and the consumer,
we can talk about overflows and overflow strategies.

The OverflowStrategy parameter dictates the strategy of the used buffer. """

  println(description)

  Thread sleep 500L
  println("-----\n")
}
