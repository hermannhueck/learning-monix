package monixdoc.execution.atomic

import java.util.concurrent.atomic.AtomicReference

import monix.execution.atomic.Atomic

object App03OtherPrimitives extends App {

  println("\n-----")

  // -----  Java's AtomicReference uses "equality by reference  ;-(

  val jRef = new AtomicReference(0.0)

  val isSuccess = jRef.compareAndSet(0.0, 100.0)
  // isSuccess: Boolean = false  // !!! but should be true :-(   This fails due to Boxing/Unboxing!

  // ----- Atomic in Monix

  val ref = Atomic(0.0)
  // ref: monix.execution.atomic.AtomicDouble = monix.execution.atomic.AtomicDouble@61414898

  ref.compareAndSet(0.0, 100.0)
  // res9: Boolean = true  // !!!   :-)

  ref.incrementAndGet()
  // res10: Double = 101.0

  val ref2 = Atomic('a')
  // ref: monix.execution.atomic.AtomicChar = monix.execution.atomic.AtomicChar@5a556dd3

  ref2.incrementAndGet()
  // res11: Char = b

  ref2.incrementAndGet()
  // res12: Char = c

  println("-----\n")
}
