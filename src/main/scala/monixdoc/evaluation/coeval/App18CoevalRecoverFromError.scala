package monixdoc.evaluation.coeval

import monix.eval.Coeval

import scala.util.Try

object App18CoevalRecoverFromError extends App {

  println("\n-----")

  val source = Coeval.raiseError[String](new IllegalStateException)

  val recovered0: Coeval[String] = source.onErrorHandleWith {
    case _: IllegalStateException =>
      // Oh, we know about illegal states, recover it
      Coeval.now("Recovered!")
    case other =>
      // We have no idea what happened, raise error!
      Coeval.raiseError(other)
  }

  private val res0: Try[String] = recovered0.runTry
  // res0: Try[String] = Success(Recovered!)
  println(res0)


  val recovered1: Coeval[String] = source.onErrorRecoverWith {
    case _: IllegalStateException =>
      // Oh, we know about illegal states, recover it
      Coeval.now("Recovered!")
  }

  private val res1: Try[String] = recovered1.runTry
  // res1: Try[String] = Success(Recovered!)
  println(res1)


  val recovered2: Coeval[String] = source.onErrorHandle {
    case _: IllegalStateException =>
      // Oh, we know about illegal states, recover it
      "Recovered!"
    case other =>
      throw other // Rethrowing
  }

  private val res2: Try[String] = recovered2.runTry
  // res2: Try[String] = Success(Recovered!)
  println(res2)




  val recovered3: Coeval[String] = source.onErrorRecover {
    case _: IllegalStateException =>
      // Oh, we know about illegal states, recover it
      "Recovered!"
  }

  private val res3: Try[String] = recovered3.runTry
  // res3: Try[String] = Success(Recovered!)
  println(res3)

  println("-----\n")
}
