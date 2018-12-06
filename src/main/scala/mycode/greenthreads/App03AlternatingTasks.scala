package mycode.greenthreads

import monix.eval.Task
import monix.execution.Scheduler

object App03AlternatingTasks extends App {

  implicit val singleThreadScheduler: Scheduler = Scheduler.singleThread("SingleThreadScheduler")

  println("\n-----")
  println("Main Thread: " + Thread.currentThread.getName + "\n")

  def newTask(i: Int): Task[Int] =
    Task { printTask(i) }
      .map { i => Thread.`yield`; i }
      .map { longRunningTask }
      .map { i => Thread.`yield`; i }
      .map { longRunningTask }
      .map { i => Thread.`yield`; i }
      .map { longRunningTask }
      .map { i => Thread.`yield`; i }
      .map { longRunningTask }


  newTask(1) runAsync println
  newTask(2) runAsync println

  Thread.sleep(12000L)
  println("-----\n")
}
