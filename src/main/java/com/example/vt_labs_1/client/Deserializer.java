package com.example.vt_labs_1.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class Deserializer {

    public Deserializer get() {
        return new Deserializer();
    }

    public Object deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        buffer.mark();
        byte[] buf = buffer.array();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
