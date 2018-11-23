package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App11cFibWithCoevalFlatMap extends App {

  println("\n-----")

  def fib(cycles: Int, a: BigInt = 0, b: BigInt = 1): Coeval[BigInt] =
    Coeval.eval(cycles > 0).flatMap {
      case true =>
        fib(cycles-1, b, a+b)
      case false =>
        Coeval.now(b)
    }

  val result = fib(5)

  println(result.value)

  println("-----\n")
}
