package house.general

import java.io.{InputStreamReader, Reader}

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object App {
  val config = loadConfig

  implicit val system = ActorSystem.create("circuit-breaker")

  val handler = system.actorOf(Props(classOf[Api]), name = "webhooks-api-handler")

  def main(args: Array[String]) = {
    IO(Http) ! Http.Bind(handler, interface = "0.0.0.0", port = config.port)
  }

  private def loadConfig = {
    val reader: Reader = new InputStreamReader(getClass.getResourceAsStream("/config.hocon"))
    val config = AppConfig.loadConfig(reader)
    reader.close()
    config
  }
}

