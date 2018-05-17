package house.services

import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.{Paths, StandardOpenOption}

import grizzled.slf4j.Logger
import house.config.ServerConfig
import house.thrift.{ClientService, Entry}
import org.apache.thrift.{TDeserializer, TSerializer}
import org.apache.thrift.server.TThreadPoolServer
import org.apache.thrift.transport.{TServerSocket, TServerTransport}

class ClientServer(config: ServerConfig, kvStore: KVStore) {
  import ClientServer._

  val handler = new ClientHandler(kvStore)
  val processor = new ClientService.Processor(handler)

  val serverTransport: TServerTransport = new TServerSocket(config.clientPort)
  // val server: TServer = new TSimpleServer(new TServer.Args(serverTransport).processor(processor))
  val server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor))

  def start() = {
    val simple = new Runnable() {
      def run() {
        try {
          logger.info(s"Starting client server on ${config.clientPort}")
          server.serve()
        } catch {
          case e: Exception =>
            logger.error(e)
            throw e
        }
      }
    }
    new Thread(simple, "ClientServer").start()
  }

  def stop() = {
    server.stop()
  }
}

object ClientServer {
  val logger = Logger(getClass)

//  def main(args: Array[String]): Unit = {
//    val fileName = "/Users/user/workspace/house/test"
//    val channel = FileChannel.open(Paths.get(fileName), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
//
//
//    val entry = new Entry(1, "k", "v")
//    val serializedEntry = new TSerializer().serialize(entry)
//
//    val bb = ByteBuffer.allocate(4 + serializedEntry.size)
//    bb.clear()
//    bb.putInt(serializedEntry.size)
//    bb.put(serializedEntry)
//    bb.flip()
//
//    while(bb.hasRemaining){
//      channel.write(bb)
//    }
//
//    val inChannel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ)
//    val sizeBB = ByteBuffer.allocate(4)
//    val size = inChannel.read(sizeBB)
//    sizeBB.flip()
//
//    val sizeToRead = sizeBB.getInt()
//
//    println(s"size to read $sizeToRead")
//
//    val newBB = ByteBuffer.allocate(sizeToRead)
//    val sizeRead = inChannel.read(newBB)
//
//    val readEntry = new Entry()
//    new TDeserializer().deserialize(readEntry, newBB.array())
//
//    println(s"Entry -> $readEntry")
//
//  }
}