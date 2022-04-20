package server;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    private ServerSocketChannel server;
    private ByteBuffer buffer = ByteBuffer.allocate(1000000);
    private SocketChannel socketChannel;
    public Boolean isBusy = false;

    public Server(int serverPort) throws IOException {
        try {
            System.out.println("Starting server...");
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(serverPort));
            server.configureBlocking(false);
            System.out.println("Server is available");
        }
        catch (Exception e){
            System.out.println("Port is unavailable.");
            System.exit(0);
        }
    }

    public Object receive() {
        while (true) {
            try {
                if (!isBusy) {
                    socketChannel = server.accept();
                }
                if (socketChannel != null&&socketChannel.isOpen()) {
                    int data = socketChannel.read(buffer);
                    System.out.println(data);
                    buffer.flip();
                    int limit = buffer.limit();
                    byte bytes[] = new byte[limit];
                    buffer.get(bytes, 0, limit);
                    buffer.clear();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Object object = objectInputStream.readObject();
                    objectInputStream.close();
                    byteArrayInputStream.close();
                    return object;
                }
            } catch (IOException e) {
                break;
            } catch (NullPointerException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
