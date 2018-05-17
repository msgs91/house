package house

import java.io.{FileInputStream, InputStreamReader, Reader}
import java.nio.file.{Files, LinkOption, Path, Paths}
import java.util.concurrent.CountDownLatch

import com.typesafe.config.ConfigFactory
import grizzled.slf4j.Logger
import house.config.ServerConfig
import house.services.{ClientServer, KVLog, KVStore, PeerServer}

object HouseMain {

  val logger = Logger(getClass)

  def main(args: Array[String]): Unit = {
    val config = loadConfig(args(0))
    setup(config)

    val latch = new CountDownLatch(1)

    val kvStore = new KVStore(config)
    kvStore.start()

//    val peerServer = new PeerServer(config, kvStore)
//    peerServer.start()

    val clientServer = new ClientServer(config, kvStore)
    clientServer.start()

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() = {
        logger.info(s"Starting shutdown")
        latch.countDown()
      }
    })
    latch.await()
    logger.info(s"Bye")
  }

  def loadConfig(configFilePath: String) = {
    val reader: Reader = new InputStreamReader(new FileInputStream(configFilePath))
    val config = ConfigFactory.parseReader(reader)
    reader.close()
    val serverConfig = ServerConfig(config)
    logger.info(s"Success loaded server config")
    serverConfig
  }

  def setup(config: ServerConfig) = {
    val path = Paths.get(config.dataDir)
    if(!Files.exists(path)){
      //TODO file attributes ?
      Files.createDirectory(path)
      logger.info(s"Created data directory ${config.dataDir}")
    }
  }

}
