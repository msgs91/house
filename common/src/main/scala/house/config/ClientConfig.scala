package house.config

import com.typesafe.config.Config
import scala.collection.JavaConverters._

case class ClientConfig(config: Config) {
  val nodes = config.getStringList("nodes").asScala.toList
}
