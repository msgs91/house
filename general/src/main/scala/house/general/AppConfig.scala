package house.general

import java.io.Reader

import com.typesafe.config.{Config, ConfigFactory}

case class AppConfig(config: Config) {
  val host = "0.0.0.0"
  val port = 8001
}

object AppConfig {
  def loadConfig(reader: Reader) = {
    val config = ConfigFactory.parseReader(reader)
    AppConfig(config)
  }
}
