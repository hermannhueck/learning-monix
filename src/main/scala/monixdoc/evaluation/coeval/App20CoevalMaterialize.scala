package monixdoc.evaluation.coeval

import monix.eval.Coeval

import scala.util.{Failure, Random, Success, Try}

object App20CoevalMaterialize extends App {

  println("\n-----")

  val source: Coeval[Int] = Coeval.raiseError[Int](new IllegalStateException)

  val materialized: Coeval[Try[Int]] = source.materialize

  println(materialized.value)

  // Now we can flatMap over both success and failure:
  val recovered: Coeval[Int] = materialized.flatMap {
    case Success(value) => Coeval.now(value)
    case Failure(_) => Coeval.now(0)
  }

  println(recovered.value)

  println("-----\n")
}
