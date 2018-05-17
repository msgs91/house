package house

import java.io.{FileInputStream, InputStreamReader, Reader}

import com.typesafe.config.ConfigFactory
import grizzled.slf4j.Logger
import house.config.{ClientConfig, ServerConfig}
import house.models.Url
import house.thrift.ClientService
import org.apache.thrift.TException
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TSocket

import scala.util.Random

object HouseClient {
  val logger = Logger(getClass)

  def main(args: Array[String]) {
    val config = loadConfig(args(0))
    val url = Url(config.nodes(0))
    println(s"Connecting to $url")

    try {
      val transport = new TSocket(url.host, url.port)
      transport.open()
      logger.info(s"Transport opened")
      val protocol = new TBinaryProtocol(transport)
      val client = new ClientService.Client(protocol)
      perform(client)
      transport.close()
    } catch {
      case e: TException =>
        throw e
    }
  }

  private def perform(client: ClientService.Client) = {
    println(s"Starting to push data")
    (1 to 10) foreach { x =>
      val key = s"key$x"
      val value = s"val$x"
      println(s"Writing $key $value")
      val reply = client.write(key, value)
    }
  }

  def loadConfig(configFilePath: String) = {
    val reader: Reader = new InputStreamReader(new FileInputStream(configFilePath))

    val config = ConfigFactory.parseReader(reader)
    reader.close()
    val clientConfig = ClientConfig(config)
    logger.info(s"Success loaded client config")
    clientConfig
  }
}
