package monixdoc.execution.atomic

import monix.execution.atomic.Atomic
import monix.execution.atomic.PaddingStrategy.{Left64, LeftRight256}

object App06CachePadding extends App {

  println("\n-----")

  // Applies padding to the left of the value for a cache line
  // of 64 bytes
  val ref1 = Atomic.withPadding(1, Left64)

  // Applies padding both to the left and the right of the value
  // for a total object size of at least 256 bytes
  val ref2 = Atomic.withPadding(1, LeftRight256)

  println("-----\n")
}
