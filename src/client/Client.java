package client;

import client.commands.AbstractCommand;
import client.data.Movie;

import java.io.*;
import java.net.Socket;

public class Client {

    private final String host;
    private final int port;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final Socket socket;

    public Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socket = new Socket(host, port);
        System.out.println("Клиент подключился к серверу.");
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void uploadCommand(AbstractCommand command) throws IOException {
        out.writeObject(command);
        out.flush();
    }

    public void uploadMovie(Movie movie) throws IOException {
        out.writeObject(movie);
        out.flush();
    }

    public void uploadText(String text) throws IOException {
        BufferedWriter outputText = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        outputText.write(text);
        outputText.flush();
    }
}
