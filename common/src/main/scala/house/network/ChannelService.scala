package house.network

import grizzled.slf4j.Logger
import house.config.ServerConfig
import house.protocol.Protocol
import house.thrift.Message
import org.zeromq.ZMQ

import scala.annotation.tailrec
import scala.collection.mutable
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class ChannelService(config: ServerConfig, context: ZMQ.Context) {
  import ChannelService._

  private val sockets: mutable.HashMap[Int, SendSocket] = new mutable.HashMap()

  private val listener = new ClusterListener(config, context, processMessage)

  private val ackMessage = Protocol.getAckMessage(config.id)
  private val amLeaderMessage = Protocol.getAmLeaderMessage(config.id)

  private var currentLeader: Int = config.id

  init()

  def processMessage(message: Message) : Message = {
    logger.debug(s"Got message ${message.msg}")
    message.msg match {
      case Protocol.Join =>
        logger.debug(s"Processing join")
        val peerId = message.getNodeId
        if(currentLeader >= peerId){
          if(currentLeader == config.id)
            logger.info("Not leader anymore")
          currentLeader = peerId
          logger.info(s"New leader is $peerId")
          ackMessage
        } else {
          amLeaderMessage
        }

      case Protocol.AmLeader =>
        logger.info(s"Leader is ${message.getNodeId}")
        currentLeader = message.getNodeId
        ackMessage

      case Protocol.Ack =>
        logger.info(s"Leader is $currentLeader")
        ackMessage
    }
  }

  def init() = {
    config.nodes foreach { node =>
      if(node.id != config.id) {
        val socket = new SendSocket(node.endpoint, context)
        sockets += (node.id -> socket)
      }
    }
  }

  @tailrec
  private def retryIndefinitely(socket: SendSocket, fn: => Option[Message]): Option[Message] = {
    try {
      var response = fn
      var count = 1
      while (count <= 3 && response.isEmpty) {
        logger.debug("No response yet")
        response = socket.recv
        count += 1
      }
      if(response.isEmpty) throw new Exception
      response
    } catch {
      case e: Exception =>
        e.printStackTrace()
        Thread.sleep(1000)
        retryIndefinitely(socket, fn)
    }
  }

  def join(): Unit = {
    sockets map {
      case (id, socket) =>
        var resFuture = Future {
          retryIndefinitely(socket, socket.send(Protocol.getJoinMessage(config.id)))
        }

        resFuture onComplete {
          case Success(msg) =>
            //ignore reponse - no need to respond to a response
            processMessage(msg.get)
          case Failure(_) =>
        }
        resFuture
    }
  }


  def start(): Unit = {
    listener.start()
    logger.info(s"Started listener")
    sockets foreach {
      case (_, socket) =>
        socket.start()
    }
    logger.info(s"Started")
    join()
  }

  def stop() = {
    listener.stop()
    sockets foreach {
      case (_, socket) =>
        socket.stop()
    }
  }
}

object ChannelService {
  val logger = Logger(getClass)
}
