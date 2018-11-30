package monixdoc.evaluation.task

import com.sun.org.slf4j.internal.LoggerFactory
import monix.eval.Task
import monix.execution.Scheduler
import monix.execution.Scheduler.{global => default}
import monix.execution.UncaughtExceptionReporter

import scala.concurrent.duration._
import scala.language.postfixOps

object App22OverridingErrorLogging extends App {

  println("\n-----")

  val reporter = UncaughtExceptionReporter { ex =>
    val logger = LoggerFactory.getLogger(this.getClass) // override logging
    logger.error("Uncaught exception", ex)
  }

  implicit val global: Scheduler = Scheduler(default, reporter)

  val task = Task(2).delayExecution(1 second)

  task.runAsync { r =>
    throw new IllegalStateException(r.toString)
  }

  Thread.sleep(1000L)
  println("-----\n")
}
