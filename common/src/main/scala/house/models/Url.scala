package house.models

case class Url(host: String, port: Int)

object Url {
  def apply(url: String) : Url = {
    val strings = url.split(":")
    val host = strings(0)
    val port = Integer.parseInt(strings(1))
    Url(host, port)
  }
}
