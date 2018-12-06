package mycode

import monix.eval.Task
import monix.execution.Scheduler

package object greenthreads {

  def fib(cycles: Int, a: BigInt = 0, b: BigInt = 1): Task[BigInt] = {
    if (cycles > 0) {
      Task.defer(fib(cycles - 1, b, a + b))
    } else {
      println
      Task.now(b)
    }
  }

  def printTask(i: Int): Int = {
    val thread = Thread.currentThread.getName
    println(s"Task $i running on Thread: $thread")
    i
  }

  def longRunningTask(i: Int)(implicit s: Scheduler): Int = {
    printTask(i)
    fib(300000) foreach { _ => () }
    i
  }
}
