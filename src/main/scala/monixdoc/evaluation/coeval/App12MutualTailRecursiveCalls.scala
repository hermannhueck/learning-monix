package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App12MutualTailRecursiveCalls extends App {

  println("\n-----")

  def odd(n: Int): Coeval[Boolean] =
    Coeval.eval(n == 0).flatMap {
      case true => Coeval.now(false)
      case false => even(n - 1)
    }

  def even(n: Int): Coeval[Boolean] =
    Coeval.eval(n == 0).flatMap {
      case true => Coeval.now(true)
      case false => odd(n - 1)
    }

  println(even(1000000).value)

  println("-----\n")
}
