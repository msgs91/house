package house.general;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerTCPTest {

    static Selector sselector = null;

    static void print(String s) {
        System.out.println(s);
    }

    static class ReaderThread extends Thread {
        @Override
        public void run() {

            print("Started reader thread");
            try {
                SocketChannel slaveChannel = SocketChannel.open();
                slaveChannel.connect(new InetSocketAddress("localhost", 9001));
                while(true) {
                    int numChannels = sselector.selectNow();
                    if (numChannels == 0) {
                        continue;
                    }
                    print("Got channel to read");
                    Set<SelectionKey> selectedKeys = sselector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectedKeys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isReadable()) {
                            SocketChannel schannel = (SocketChannel) key.channel();
                            System.out.println(schannel.getRemoteAddress());
                            ByteBuffer buffer = ByteBuffer.allocate(64);
                            int bytesRead = schannel.read(buffer);
                            StringBuilder sb = new StringBuilder();
                            while (bytesRead != 0) {
                                buffer.flip();
                                byte[] arr = new byte[buffer.remaining()];
                                buffer.get(arr);
                                sb.append(new String(arr));
                                ByteBuffer buffer1 = ByteBuffer.wrap(arr);
                                while(buffer1.hasRemaining()){
//                                    schannel.write(buffer1);
                                    if(slaveChannel.isConnected()) {
                                        print("connected");
                                        slaveChannel.write(buffer1);
                                    } else {
                                        print("Slave not connected");
                                    }

                                }
                                buffer.clear();
                                bytesRead = schannel.read(buffer);
                            }
                            print(String.format("MASTER - Done reading %s", sb));
                        }
                        iterator.remove();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocketChannel sschannel = ServerSocketChannel.open();
        sselector = Selector.open();
        new ReaderThread().start();
        print("called read");

        sschannel.socket().bind(new InetSocketAddress(9000));

        while(true){
            SocketChannel schannel = sschannel.accept();
            schannel.configureBlocking(false);
            SelectionKey key = schannel.register(sselector, SelectionKey.OP_READ);
        }



    }
}
