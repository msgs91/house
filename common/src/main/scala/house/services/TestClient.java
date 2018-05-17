package house.services;

import house.thrift.TestService;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class TestClient {
    public static void main(String[] args) {

        try {
            TTransport transport;
            if (true) {
                transport = new TSocket("localhost", 9090);
                transport.open();
            } else {
        /*
         * Similar to the server, you can use the parameters to setup client parameters or
         * use the default settings. On the client side, you will need a TrustStore which
         * contains the trusted certificate along with the public key.
         * For this example it's a self-signed cert.
         */
                TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
                params.setTrustStore("../../lib/java/test/.truststore", "thrift", "SunX509", "JKS");
        /*
         * Get a client transport instead of a server transport. The connection is opened on
         * invocation of the factory method, no need to specifically call open()
         */
                transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
            }

            TProtocol protocol = new TBinaryProtocol(transport);
            TestService.Client client = new TestService.Client(protocol);

            perform(client);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(TestService.Client client){
        try {
            boolean reply = client.write("test", "value");
            System.out.print(reply);
        } catch(Exception e){

        } finally {

        }
    }
}



