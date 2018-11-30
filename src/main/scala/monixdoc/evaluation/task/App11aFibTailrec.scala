package monixdoc.evaluation.task

import scala.annotation.tailrec

object App11aFibTailrec extends App {

  println("\n-----")

  @tailrec
  def fib(cycles: Int, a: BigInt = 0, b: BigInt = 1): BigInt = {
    if (cycles > 0)
      fib(cycles-1, b, a + b)
    else
      b
  }

  private val result: BigInt = fib(5)

  println(result)

  Thread.sleep(1000L)
  println("-----\n")
}
