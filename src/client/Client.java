package client;

import common.commands.AbstractCommand;
import common.data.Movie;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client implements Serializable {

    private final String host;
    private final int port;
    private final SocketChannel client;
    private ByteBuffer buffer;

    public Client(String h, int p) throws IOException {
        this.host = h;
        this.port = p;
        client = SocketChannel.open(new InetSocketAddress(host, p));
        System.out.println("Клиент подключился к серверу.");
        buffer = ByteBuffer.allocate(1000000);
    }

    public void uploadObject(Object object) throws IOException {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bytesOut);
        outputStream.writeObject(object);
        outputStream.flush();
        outputStream.close();
        byte[] bytes = bytesOut.toByteArray();
        bytesOut.close();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        client.write(buffer);
    }

    public void uploadText(String text) throws IOException {
        buffer = ByteBuffer.wrap(text.getBytes());
        client.write(buffer);
        buffer.clear();
    }

    public boolean downloadResult() throws IOException {
        buffer.clear();
        client.read(buffer);
        int flag = buffer.get(0);
        buffer = ByteBuffer.allocate(1000000);
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String downloadAnswer() throws Exception {
        String answer = null;
        while (answer == null) {
            int data = client.read(buffer);
            if (!(data == -1) && (!(data == 0))) {
                Object o = byteBufferToObject(buffer);
                answer = (String) o;
                buffer = ByteBuffer.allocate(1000000);
            }
        }
        return answer;
    }

    private Object byteBufferToObject(ByteBuffer byteBuffer)
            throws Exception {
        byte[] bytes = byteBuffer.array();
        return deSerializer(bytes);
    }

    private Object deSerializer(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(bytes));
        Object object = null;
        boolean check = false;
        while (!(check)) {
            try {
                object = objectInputStream.readObject();
                check = true;
            } catch (EOFException ex) {
                check = false;
            }
        }
        return object;
    }
}
