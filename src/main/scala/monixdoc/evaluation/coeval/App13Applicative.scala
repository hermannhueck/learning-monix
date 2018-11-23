package monixdoc.evaluation.coeval

import monix.eval.Coeval

object App13Applicative extends App {

  println("\n-----")

  val locationCoeval: Coeval[String] = Coeval.eval(???)
  val phoneCoeval: Coeval[String] = Coeval.eval(???)
  val addressCoeval: Coeval[String] = Coeval.eval(???)

  // Ordered operations based on flatMap ...
  val aggregate1: Coeval[String] =
    for {
      location <- locationCoeval
      phone <- phoneCoeval
      address <- addressCoeval
    } yield {
      "Gotcha!"
    }

  // Unordered operations with Coeval as Applicative
  val aggregate2: Coeval[String] =
    Coeval.zip3(locationCoeval, phoneCoeval, addressCoeval).map {
      case (location, phone, address) => "Gotcha!"
    }

  val aggregate3: Coeval[String] =
    Coeval.map3(locationCoeval, phoneCoeval, addressCoeval) {
      (location, phone, address) => "Gotcha!"
    }

  // mapN not yet available in Monix !!!

  println("-----\n")
}
