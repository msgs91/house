package house.services

import house.thrift.ClientService

class ClientHandler(kVStore: KVStore) extends ClientService.Iface {

  override def write(key: String, value: String): Boolean = {
    kVStore.write(key, value)
  }

  override def read(key: String): String = {
    kVStore.read(key)
  }

}
