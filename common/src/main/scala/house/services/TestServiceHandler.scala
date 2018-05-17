package house.services

import house.thrift.TestService

class TestServiceHandler extends TestService.Iface {
  override def write(key: String, value: String): Boolean = {
    true
  }

  override def read(key: String): String = {
    null
  }
}
