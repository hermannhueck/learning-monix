package monixdoc.evaluation.coeval

import monix.eval.Coeval

import scala.util.{Failure, Success, Try}

object App21CoevalDematerialize extends App {

  println("\n-----")

  val source: Coeval[Int] = Coeval.raiseError[Int](new IllegalStateException)

  // Exposing errors
  val materialized = source.materialize
  // materialize: Coeval[Try[Int]] = ???

  println(materialized.value)

  // Hiding errors again
  val dematerialized = materialized.dematerialize
  // dematerialized: Coeval[Int] = ???

  println(dematerialized.value)

  println("-----\n")
}
