package com.example.vt_labs_1.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Deserializer {

    public Deserializer get() {
        return new Deserializer();
    }

    public Object deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException, InterruptedException {
        buffer.mark();
        byte[] buf = buffer.array();
//        System.out.println(new String(buf, StandardCharsets.UTF_8));
        Thread.sleep(150);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
