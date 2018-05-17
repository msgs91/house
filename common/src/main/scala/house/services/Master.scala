//package kaadu.services
//
//import java.util.UUID
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.atomic.AtomicBoolean
//
//import kaadu.config.MasterConfig
//import kaadu.protocol.{Messages, Protocol}
//import kaadu.thrift.Message
//import org.apache.thrift.TSerializer
//import org.zeromq.ZMQ
//
//class Master(config: MasterConfig, latch: CountDownLatch) {
//  private val context = ZMQ.context(1)
//  private val masterSocket = context.socket(ZMQ.REP)
//
//  private val serialize = new TSerializer().serialize _
//
//  private val closed = new AtomicBoolean(false)
//
//  def start() = {
//    masterSocket.bind(s"tcp://0.0.0.0:${config.port}")
//    listen()
//  }
//
//  def listen() = {
//    while(!closed.get()){
//      val receivedBytes = masterSocket.recv()
//      val msg = Messages.getMessage(receivedBytes)
//      println(s"Got $msg")
//      send(new Message(UUID.randomUUID.toString, Protocol.Version, Messages.Ack))
//    }
//  }
//
//  def send(msg: Message) = {
//    val serialized = serialize(msg)
//    synchronized {
//      masterSocket.send(serialized)
//    }
//  }
//
//  def stop() = {
//    closed.set(true)
//    masterSocket.close()
//    context.term()
//    latch.countDown()
//  }
//}
