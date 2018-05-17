package house.config

import com.typesafe.config.Config
import house.models.Node

import scala.collection.JavaConverters._


case class ServerConfig(private val config: Config) {

  val clusterPort = config.getInt("clusterPort")
  val clientPort = config.getInt("clientPort")

  val id = config.getInt("id")

  val endpoints = config.getStringList("nodes").asScala.toList

  val nodes: List[Node] = endpoints.zipWithIndex map { case (node, index) => Node(index, node)}

  val dataDir = config.getString("dataDir")
}
