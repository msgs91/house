package house.services

import java.util.concurrent.ConcurrentHashMap

import house.config.ServerConfig

class KVStore(config: ServerConfig) {

  val kvLog = new KVLog(config, this)

  private val map = new ConcurrentHashMap[String, String]()

  def start() = {
    init()
    kvLog.start()
  }

  def contains(key: String) : Boolean = {
    map.contains(key)
  }

  def write(key: String, value: String) : Boolean = {
    try {
      kvLog.write(key, value)
      map.put(key, value)
      true
    } catch {
      case _: Throwable =>
        false
    }
  }

  def read(key: String) : String = {
    map.get(key)
  }

  private def init() = {

  }

}
