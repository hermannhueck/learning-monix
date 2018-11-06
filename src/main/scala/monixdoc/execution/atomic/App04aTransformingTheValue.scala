package monixdoc.execution.atomic

import java.util.concurrent.atomic.AtomicReference

import monix.execution.atomic.Atomic

import scala.collection.immutable.Queue

object App04aTransformingTheValue extends App {

  println("\n-----")

  def pushElementAndGet[T <: AnyRef, U <: T](ref: AtomicReference[Queue[T]], elem: U): Queue[T] = {

    var continue = true
    var update = null

    while (continue) {
      var current: Queue[T] = ref.get()
      var update = current.enqueue(elem)
      continue = !ref.compareAndSet(current, update)
    }

    update
  }

  println("-----\n")
}
