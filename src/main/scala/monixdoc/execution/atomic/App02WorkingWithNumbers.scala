package monixdoc.execution.atomic

import monix.execution.atomic.{Atomic, AtomicAny, AtomicInt, AtomicLong}

object App02WorkingWithNumbers extends App {

  println("\n-----")

  val ref = Atomic(BigInt(1))
  // ref: monix.execution.atomic.AtomicNumberAny[scala.math.BigInt] = monix.execution.atomic.AtomicNumberAny@1735900a

  // now we can increment a BigInt
  println(ref.incrementAndGet()) // 2

  // or adding to it another value
  println(ref.addAndGet(BigInt("329084291234234"))) // 329084291234236

  println("-----\n")
}
