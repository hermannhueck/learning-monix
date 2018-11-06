package monixdoc.execution.scheduler

import monix.execution.schedulers.TestScheduler

import scala.concurrent.duration._

object App05InjectingTimeAndTest extends App {

  println("\n-----")

  val testScheduler = TestScheduler()

  testScheduler.execute(new Runnable {
    def run() = println("Immediate!")
  })

  testScheduler.scheduleOnce(1.second) {
    println("Delayed execution!")
  }

  Thread.sleep(2000L)

  // Now we can fake it. Executes immediate tasks,
  // on the current thread:
  testScheduler.tick()
  // => Immediate!

  Thread.sleep(2000L)

  // Simulate passage of time, current thread:
  testScheduler.tick(5.seconds)
  // => Delayed execution!

  println("-----\n")
}
