package monixdoc.execution.atomic

import monix.execution.atomic.{Atomic, AtomicAny, AtomicInt, AtomicLong}

object App05Boxing extends App {

  println("\n-----")

  val ref1 = Atomic(1)
  // ref: monix.execution.atomic.AtomicInt = AtomicInt(1)

  val ref2 = Atomic(1L)
  // ref: monix.execution.atomic.AtomicLong = AtomicLong(1)

  val ref3 = Atomic(true)
  // ref: monix.execution.atomic.AtomicBoolean = AtomicBoolean(true)

  val ref4 = Atomic("")
  // ref: monix.execution.atomic.AtomicAny[String] = monix.execution.atomic.AtomicAny@44b7450c

  println("-----\n")
}
