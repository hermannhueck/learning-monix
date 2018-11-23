package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App11bFibWithCoevalDefer extends App {

  println("\n-----")

  def fib(cycles: Int, a: BigInt = 0, b: BigInt = 1): Coeval[BigInt] =
    if (cycles > 0)
      Coeval.defer(fib(cycles-1, b, a + b))
    else
      Coeval.now(b)

  val result = fib(5)

  println(result.value)

  println("-----\n")
}
