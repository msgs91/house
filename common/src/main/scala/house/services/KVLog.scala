package house.services

import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.{Files, Paths, StandardOpenOption}

import grizzled.slf4j.Logger
import house.config.ServerConfig
import house.thrift.Entry
import org.apache.thrift.{TDeserializer, TSerializer}

class KVLog(config: ServerConfig, kVStore: KVStore) {
  import KVLog._

  private val FilePath = Paths.get(s"${config.dataDir}/kv")
  private val writeChannel = FileChannel.open(FilePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)

  def write(key: String, value: String) = synchronized {
    val entry = new Entry(1, key, value)
    val serializedEntry = new TSerializer().serialize(entry)
    val bb = ByteBuffer.allocate(RecordSizeBytes + serializedEntry.size)
    bb.clear()
    bb.putInt(serializedEntry.size)
    bb.put(serializedEntry)
    bb.flip()

    while (bb.hasRemaining) {
      writeChannel.write(bb)
    }
    writeChannel.force(false)
  }

  def start() = {
    if(!Files.exists(FilePath)){
      Files.createFile(FilePath)
    } else {

      val inChannel = FileChannel.open(FilePath, StandardOpenOption.READ)

      val sizeBB = ByteBuffer.allocate(4)
      var size = inChannel.read(sizeBB)
      while(size != -1) {
        sizeBB.flip()
        val entrySize = sizeBB.getInt()

        val newBB = ByteBuffer.allocate(entrySize)
        val sizeRead = inChannel.read(newBB)
        newBB.flip()

        val readEntry = new Entry()
        new TDeserializer().deserialize(readEntry, newBB.array())

        println(s"Entry -> $readEntry")
        kVStore.write(readEntry.key, readEntry.value)

        sizeBB.clear()
        size = inChannel.read(sizeBB)
      }
      inChannel.close()
    }
  }

  def stop() = {
    writeChannel.close()
  }
}

object KVLog {
  val logger = Logger(getClass)
  val RecordSizeBytes = 4
}
