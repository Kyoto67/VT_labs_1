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
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final SocketChannel client;
    private ByteBuffer buffer;
    private Socket socket;

    public Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        client = SocketChannel.open(new InetSocketAddress(host, 1337));
        System.out.println("Клиент подключился к серверу.");
        buffer = ByteBuffer.allocate(256);
    }

    public void uploadCommand(AbstractCommand command) throws IOException {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bytesOut);
        outputStream.writeObject(command);
        outputStream.flush();
        ByteBuffer buffer = ByteBuffer.wrap(bytesOut.toByteArray());
        client.write(buffer);
        bytesOut.close();
    }

    public void uploadMovie(Movie movie) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(movie);
        out.flush();
    }

    public void uploadText(String text) throws IOException {
        buffer = ByteBuffer.wrap(text.getBytes());
        client.write(buffer);
        buffer.clear();
    }
    //  out = new ObjectOutputStream(socket.getOutputStream());
    // out.writeObject(text);
    //out.flush();
    //}

    public boolean downloadResult() throws IOException, ClassNotFoundException {
        buffer.clear();
        client.read(buffer);
        int flag = buffer.get(0);
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
        //in = new ObjectInputStream(socket.getInputStream());
        //boolean result = (boolean) in.readObject();
        //return result;
    }
}
