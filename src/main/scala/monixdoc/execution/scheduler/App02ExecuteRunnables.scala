package monixdoc.execution.scheduler

import monix.execution.Scheduler.{global => scheduler}

object App02ExecuteRunnables extends App {

  println("\n-----")

  scheduler.execute(new Runnable {
    def run(): Unit = {
      println("Hello, world!")
    }
  })

  Thread.sleep(100L)
  println("-----\n")
}
