package monixdoc.execution.cancelable

import monix.execution.Cancelable
import monix.execution.cancelables.{RefCountCancelable, SerialCancelable}

object App08RefCountCancelable extends App {

  println("\n-----")

  val refs = RefCountCancelable { () =>
    println("Everything was canceled")
  }

  // acquiring two cancelable references
  val ref1 = refs.acquire()
  val ref2 = refs.acquire()

  // Starting the cancelation process
  refs.cancel()

  // This is now true, but the callback hasn't been invoked yet
  println(refs.isCanceled) // true

  // After our RefCountCancelable was canceled, this will return
  // a Cancelable.empty
  val ref3 = refs.acquire()

  println(ref3 == Cancelable.empty) // true

  // Release reference 1, nothing happens here
  ref1.cancel()

  // Release reference 2, now we can execute
  ref2.cancel()
  // => Everything was canceled

  println("-----\n")
}
