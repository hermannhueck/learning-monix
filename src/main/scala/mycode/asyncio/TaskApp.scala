package mycode.asyncio

import monix.execution.Scheduler.Implicits.global

import scala.util.{Failure, Success, Try}

object AppTask extends App {

  println("\n-----")

  {
    println("===== Task.now")
    val task = Task.now(1 + 1)

    println(task.run())

    task.runToFuture foreach println

    task.runOnComplete {
      case Failure(t) => println(t.toString)
      case Success(a) => println(a)
    }

    task.runAsync {
      case Left(t) => println(t.toString)
      case Right(a) => println(a)
    }
  }

  {
    println("===== Task.raiseError")
    val task = Task.raiseError(new IllegalStateException("state is illegal"))

    println( Try {
      task.run()
    })

    task.runToFuture foreach println // this will not print anything in case of error

    task.runOnComplete {
      case Failure(t) => println(t.toString)
      case Success(a) => println(a)
    }

    task.runAsync {
      case Left(t) => println(t.toString)
      case Right(a) => println(a)
    }
  }

  Thread.sleep(1000L)
  println("-----\n")
}
