package monixdoc.evaluation.coeval

import monix.eval.Coeval
import monix.execution.Scheduler.Implicits.global

object App03CoevalToTask extends App {

  println("\n-----")

  val coeval = Coeval.eval(1 + 1)
  // coeval: monix.eval.Coeval[Int] = Coeval.Always$2037201271

  val task = coeval.task
  // task: monix.eval.Task[Int] = Task.Eval$1214589719

  task runAsync println

  println("-----\n")
}
