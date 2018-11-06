package monixdoc.execution.atomic

import java.util.concurrent.atomic.AtomicReference

import monix.execution.atomic.{Atomic, AtomicAny}

import scala.collection.immutable.Queue

object App04bTransformingTheValue extends App {

  println("\n-----")

  val ref: AtomicAny[Queue[String]] = Atomic(Queue.empty[String])
  // ref: monix.execution.atomic.AtomicAny[scala.collection.immutable.Queue[String]] = monix.execution.atomic.AtomicAny@8ecc61c

  // Transforms the value and returns the update
  ref.transformAndGet(_.enqueue("hello"))
  // res15: scala.collection.immutable.Queue[String] = Queue(hello)

  // Transforms the value and returns the current one
  ref.getAndTransform(_.enqueue("world"))
  // res17: scala.collection.immutable.Queue[String] = Queue(hello)

  // We can be specific about what we want extracted as a result
  val s1: String = ref.transformAndExtract { current =>
    val (result, update) = current.dequeue
    (result, update)
  }
  // res19: String = hello
  println(s1)

  // Or the shortcut, because it looks so good
  val s2: String = ref.transformAndExtract(_.dequeue)
  // res21: String = world
  println(s2)

  println("-----\n")
}
