package house.services

import house.thrift.PeerService

class PeerHandler extends PeerService.Iface {

  override def replicate(key: String, value: String): Boolean = {
    false
  }
}
