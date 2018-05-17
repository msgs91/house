package house.protocol

import house.thrift.Message
import org.apache.thrift.{TDeserializer, TSerializer}

object Protocol {
  val Version = 1
  val Join = "join"
  val HeartBeat = "heartbeat"
  val Ack = "ack"
  val AmLeader = "amleader"

  def getMessage(bytes: Array[Byte]): Message = {
    val msg = new Message()
    new TDeserializer().deserialize(msg, bytes)
    msg
  }

  def getBytes(message: Message): Array[Byte] = {
    new TSerializer().serialize(message)
  }

  def getAckMessage(nodeId: Int) : Message = {
    new Message(Protocol.Version, nodeId, Ack)
  }

  def getAmLeaderMessage(nodeId: Int): Message = {
    new Message(Protocol.Version, nodeId, AmLeader)
  }

  def getJoinMessage(nodeId: Int) = {
    new Message(Protocol.Version, nodeId, Join)
  }

  def getHeartBeatMessage(nodeId: Int) = {
    new Message(Protocol.Version, nodeId, Join)
  }

}