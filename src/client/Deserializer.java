package client;

import java.io.*;
import java.nio.ByteBuffer;

public class Deserializer {

    public Deserializer get() {
        return new Deserializer();
    }

    public Object deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        byte[] buf = buffer.array();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object o=objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return o;
    }
}
