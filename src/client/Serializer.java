package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Serializer {

    ObjectOutputStream outputStream;
    ByteArrayOutputStream bytesOut;

    public Serializer(){

    }
    public ByteBuffer serialize(Object o) throws IOException {
        bytesOut = new ByteArrayOutputStream();
        outputStream = new ObjectOutputStream(bytesOut);
        outputStream.writeObject(o);
        outputStream.flush();
        outputStream.close();
        byte[] bytes = bytesOut.toByteArray();
        bytesOut.close();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer;
    }
}
