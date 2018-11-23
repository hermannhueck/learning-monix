package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App01Coeval extends App {

  println("\n-----")

  val coeval: Coeval[String] = Coeval {
    println("Effect!")
    "Hello!"
    // throw new RuntimeException("HelloError!")
  }
  // coeval: monix.eval.Coeval[String] = Coeval.Always$425956525

  // And we can handle errors explicitly:
  import scala.util.{Failure, Success}

  // evaluates again
  coeval.runTry match { // returns a Try
    case Success(value) => println(value)
    case Failure(ex) => System.err.println(ex)
  }

  coeval.runTry.toEither match { // same as runAttempt
    case Right(value) => println(value)
    case Left(ex) => System.err.println(ex)
  }

  coeval.runAttempt match { // returns an Either
    case Right(value) => println(value)
    case Left(ex) => System.err.println(ex)
  }

  coeval.run match { // returns a Coeval.Eager
    case Coeval.Now(value) => println(value)
    case Coeval.Error(ex) => System.err.println(ex)
  }

  // Coeval has lazy behavior, so nothing
  // happens until being evaluated:
  val effect: String = coeval.value
  //=> Effect!
  println(effect)
  //=> Hello!

  println("-----\n")
}
