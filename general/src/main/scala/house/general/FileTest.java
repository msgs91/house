package house.general;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

public class FileTest {

    static String dir = "/Users/user/workspace/logs-house";
    static String fileStr = dir + "/message.log";
    static Path dirPath = Paths.get(dir);

    public static void tcp() {

    }

    public static void main(String[] args) throws Exception {
        boolean exists = Files.exists(dirPath);
        if(!exists){
            try {
                Files.createDirectory(dirPath);
            } catch(Exception e) {
                throw e;
            }
        }
        FileChannel appendChannel = FileChannel.open(Paths.get(fileStr), APPEND,  CREATE);
        String testData = String.format("test data\n");
        ByteBuffer buffer = ByteBuffer.allocate(testData.getBytes().length);
        buffer.clear();
        buffer.put(testData.getBytes());
        buffer.flip();
        while(buffer.hasRemaining()){
            appendChannel.write(buffer);
        }
        System.out.println("done");
        buffer.clear();
        appendChannel.close();

    }
}
