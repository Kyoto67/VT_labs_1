package server;

import client.commands.AbstractCommand;
import client.commands.Add;
import client.data.Movie;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;
    private final ServerSocket server;
    private final String host;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Server(int port) throws IOException {
        this.port = port;
        this.host = "localhost";
        server = new ServerSocket(port);
        System.out.println("Сервер поднят.");
        socket = server.accept();
        System.out.println("Клиент подключился к серверу.");
    }

    public AbstractCommand downloadCommand() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        AbstractCommand command = (AbstractCommand) in.readObject();
        return command;
    }

    public Movie downloadMovie() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        Movie movie = (Movie) in.readObject();
        return movie;
    }
    public String downloadText() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        String inputText = (String) in.readObject();
        return inputText;
    }
}
