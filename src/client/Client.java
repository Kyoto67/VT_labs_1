package client;

import commands.Connect;
import exceptions.Disconnect;
import utility.User;

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
    private User user;

    public Client(String h, int p, User u) throws Disconnect {
        this.host = h;
        this.port = p;
        this.user = u;
        serializer = new Serializer();
        deserializer = new Deserializer();
        buffer = ByteBuffer.allocate(1024*100);
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
                buffer = ByteBuffer.allocate(1024*100);
                buffer.position(0);
                return o;
            } catch (Exception e) {
//                 
            }
        }
    }

    private void close() throws IOException {
        client.close();
    }

    private void findServer() throws Disconnect {
        System.out.println("Подключаюсь к серверу...");
        String result = run(new Connect("connect","подключение к серверу.", user));
        System.out.println(result);
        if(!(result.equals("Авторизация успешна.\n" +
                "Выполнение завершено.\n") | result.equals("Зарегистрирован новый пользователь.\n" +
                "Выполнение завершено.\n"))) {
            throw new Disconnect("Подключение не установлено");
        }
    }

    public User getUser() {
        return user;
    }
}