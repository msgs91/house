package house.services

import grizzled.slf4j.Logger
import house.config.ServerConfig
import house.thrift.PeerService
import org.apache.thrift.server.TThreadPoolServer
import org.apache.thrift.transport.{TServerSocket, TServerTransport}

class PeerServer(config: ServerConfig, kvStore: KVStore) {
  import PeerServer._

  val handler = new PeerHandler
  val processor = new PeerService.Processor(handler)

  val serverTransport: TServerTransport = new TServerSocket(config.clusterPort)
  // val server: TServer = new TSimpleServer(new TServer.Args(serverTransport).processor(processor))
  val server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor))

  def start() = {
    val simple = new Runnable() {
      def run() {
        try {
          logger.info(s"Starting peer server on ${config.clusterPort}")
          server.serve()
        } catch {
          case e: Exception =>
            logger.error(e)
            throw e
        }
      }
    }
    new Thread(simple, "PeerServer").start()
  }

  def stop() = {
    server.stop()
  }
}

object PeerServer {
  val logger = Logger(getClass)
}