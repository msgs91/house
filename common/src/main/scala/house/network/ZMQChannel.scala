package house.network

import java.util.concurrent.atomic.AtomicBoolean

import grizzled.slf4j.Logger
import house.config.ServerConfig
import house.protocol.Protocol
import house.thrift.Message
import org.apache.thrift.TSerializer
import org.zeromq.ZMQ

object SendSocket {
  val logger = Logger(getClass)
}

class SendSocket(peerEndpoint: String, context: ZMQ.Context) {
  import SendSocket._

  val reqSocket: ZMQ.Socket = context.socket(ZMQ.REQ)
  reqSocket.setReceiveTimeOut(5000)
  reqSocket.setSendTimeOut(5000)
  reqSocket.setImmediate(false)
  reqSocket.setReconnectIVL(100)
  reqSocket.setReconnectIVL(1000)

  def start(): Unit = {
    val connected = reqSocket.connect(s"tcp://$peerEndpoint")
    if(!connected){
      logger.error(s"Failed to connect to $peerEndpoint")
    } else {
      logger.info(s"Connected to $peerEndpoint")
    }
  }

  def send(message: Message): Option[Message] = {
    val sent = reqSocket.send(new TSerializer().serialize(message))
    if (!sent) {
      println(s"Send error ${reqSocket.errno()}")
      if (reqSocket.errno() == ZMQ.Error.EAGAIN.getCode) {
        throw new Exception()
      }
    }
    recv
  }

  def recv : Option[Message] = {
    val receivedBytes = reqSocket.recv()
    if(receivedBytes == null) None
    else Some(Protocol.getMessage(receivedBytes))
  }

  def stop() = {
    reqSocket.close()
  }
}

class ClusterListener(serverConfig: ServerConfig, context: ZMQ.Context, handler: Message => Message) {
  val socket = context.socket(ZMQ.REP)

  val receiveThread = new Thread(new Runnable {
    override def run(): Unit = {
      receiveLoop()
    }
  }, "ClusterListenerThread")

  private val receiving = new AtomicBoolean(false)

  def start() = {
    socket.bind(s"tcp://0.0.0.0:${serverConfig.clusterPort}")
    receiving.set(true)
    receiveThread.start()
  }

  private def receiveLoop() = {
    while (receiving.get()) {
      try{
        val receivedBytes = {
          socket.recv()
        }
        val message = Protocol.getMessage(receivedBytes)
        val response = handler(message)
        socket.send(Protocol.getBytes(response))
      } catch {
        case _ =>
      }
    }
  }

  def stop() = {
    receiving.set(false)
    receiveThread.interrupt()
    socket.close()
  }
}

object ZMQChannel {
  def main(args: Array[String]): Unit = {


    Runtime.getRuntime.addShutdownHook(new Thread(new Runnable() {
      override def run() = {
      }
    }, "ShutdownHook"))

  }
}