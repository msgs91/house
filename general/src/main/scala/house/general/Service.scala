package house.general

import java.util

import scalaj.http.Http

class Service {

  private var map: java.util.HashMap[String, Int] = new util.HashMap[String, Int]()

  def put(key: String, value: Int) : Unit = {
    synchronized {
      map.put(key, value)
    }
  }

  def get(key: String) : Int = {
    synchronized {
      map.getOrDefault(key, -1)
    }
  }

}
