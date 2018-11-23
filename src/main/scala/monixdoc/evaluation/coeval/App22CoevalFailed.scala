package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App22CoevalFailed extends App {

  println("\n-----")

  val source1: Coeval[Int] = Coeval.raiseError[Int](new IllegalStateException)

  val coevalThrowable1 = source1.failed
  // throwable: Coeval[Throwable] = ???

  val try1 = coevalThrowable1.runTry
  // try1: Try[Throwable] = Success(java.lang.IllegalStateException)

  println(try1)


  val source2: Coeval[Int] = Coeval.now(1)

  val coevalThrowable2 = source2.failed
  // throwable: Coeval[Throwable] = ???

  val try2 = coevalThrowable2.runTry
  // try2: Try[Throwable] = Failure(java.util.NoSuchElementException: failed)

  println(try2)

  println("-----\n")
}
