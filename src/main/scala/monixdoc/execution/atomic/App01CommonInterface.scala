package monixdoc.execution.atomic

import monix.execution.atomic.{Atomic, AtomicAny, AtomicInt, AtomicLong}

object App01CommonInterface extends App {

  println("\n-----")

  val refInt1: Atomic[Int] = Atomic(0)
  val refInt2: AtomicInt = Atomic(0)

  val refLong1: Atomic[Long] = Atomic(0L)
  val refLong2: AtomicLong = Atomic(0L)

  val refString1: Atomic[String] = Atomic("hello")
  val refString2: AtomicAny[String] = Atomic("hello")

  println("-----\n")
}
