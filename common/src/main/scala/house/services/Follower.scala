//package kaadu.services
//
//import java.util.UUID
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.atomic.AtomicBoolean
//
//import kaadu.config.FollowerConfig
//import kaadu.protocol.{Messages, Protocol}
//import kaadu.thrift.Message
//import org.apache.thrift.TSerializer
//import org.zeromq.ZMQ
//
//class Follower(config: FollowerConfig, latch: CountDownLatch) {
//  private val context = ZMQ.context(1)
//  private val followerSocket = context.socket(ZMQ.REQ)
//
//  private val serialize = new TSerializer().serialize _
//
//  private val closed = new AtomicBoolean(false)
//
//  def start() = {
//    followerSocket.connect(s"tcp://${config.masterUrl.host}:${config.masterUrl.port}")
//    join()
//  }
//
//  def join() = {
//    send(new Message(UUID.randomUUID().toString, Protocol.Version, Messages.Join))
//    var received = false
//    var msg: Message = null
//    msg = Messages.getMessage(followerSocket.recv())
//    println(s"Got $msg")
//  }
//
//  def listen() = {
//    while(!closed.get()){
//      val receivedBytes = followerSocket.recv()
//      val msg = Messages.getMessage(receivedBytes)
//      println(s"Got $msg")
//    }
//  }
//
//  def send(msg: Message) = {
//    val serialized = serialize(msg)
//    synchronized {
//      followerSocket.send(serialized)
//    }
//  }
//
//  def stop() = {
//    closed.set(true)
//    followerSocket.close()
//    context.term()
//    latch.countDown()
//  }
//}
