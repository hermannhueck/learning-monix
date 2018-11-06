package monixdoc.execution.cancelable

// Let's simulate a race condition
import monix.execution.Cancelable
import monix.execution.Scheduler.{global => scheduler}
import monix.execution.cancelables.OrderedCancelable

object App05aOrderedCancelable extends App {

  println("\n-----")

  val c = OrderedCancelable()

  scheduler.execute(new Runnable {
    def run(): Unit =
      c.orderedUpdate(
        Cancelable(() => println("Number #2")),
        order = 2)
  })

  c.orderedUpdate(
    Cancelable(() => println("Number #1")),
    order = 1)

  println("-----\n")
}
