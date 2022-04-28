package client;

import commands.Connect;
import exceptions.Disconnect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    private String host;
    private int port;
    private SocketChannel client;
    private Serializer serializer;
    private Deserializer deserializer;
    private ByteBuffer buffer;

    public Client(String h, int p) throws Disconnect {
        this.host = h;
        this.port = p;
        serializer = new Serializer();
        deserializer = new Deserializer();
        buffer = ByteBuffer.allocate(100000);
        findServer();
    }

    public String run(Object o1){
        String out="";
        try {
            connect();
            sendObject(o1);
            out = (String) getObject();
            close();
        } catch (IOException e){
            return "Отсутствует связь с сервером.";
        }
        return out;
    }

    private void connect() throws IOException {
        client = SocketChannel.open(new InetSocketAddress(host, port));
        client.configureBlocking(false);
    }

    private void sendObject(Object object) throws IOException {
        client.write(serializer.serialize(object));
    }

    private Object getObject() {
        while (true) {
            try {
                client.read(buffer);
                Object o = deserializer.deserialize(buffer);
                buffer = ByteBuffer.allocate(100000);
                return o;
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

    private void close() throws IOException {
        client.close();
    }

    private void findServer() throws Disconnect {
        System.out.println("Подключаюсь к серверу...");
        String result = run(new Connect("connect","подключение к серверу."));
        if(!(result.equals("Выполнение успешно.\n"))) {
            throw new Disconnect("Подключение не установлено");
        }
        System.out.println(result);
    }


}