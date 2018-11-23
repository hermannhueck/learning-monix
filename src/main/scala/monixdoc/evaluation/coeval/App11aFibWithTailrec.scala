package monixdoc.evaluation.coeval

import scala.annotation.tailrec

object App11aFibWithTailrec extends App {

  println("\n-----")

  @tailrec
  def fib(cycles: Int, a: BigInt = 0, b: BigInt = 1): BigInt =
    if (cycles > 0)
      fib(cycles-1, b, a + b)
    else
      b

  val result = fib(5)

  println(result)

  println("-----\n")
}
