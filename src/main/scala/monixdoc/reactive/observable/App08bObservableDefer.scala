package monixdoc.reactive.observable

import monix.execution.Scheduler.Implicits.global
import monix.reactive._

import scala.io.Source

object App08bObservableDefer extends App {

  println("\n-----")

  def readFile(path: String): Observable[String] =
    Observable.defer {
      // The side effect won't happen until subscription
      val lines = Source.fromFile(path).getLines
      Observable.fromIteratorUnsafe(lines)
    }

  readFile("build.sbt").countL runAsync println
  readFile("build.sbt").countL foreach println

  Thread.sleep(500L)
  println("-----\n")
}
