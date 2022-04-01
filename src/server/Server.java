package server;

import client.commands.AbstractCommand;
import client.commands.Add;

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

    public void downloadCommand() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        AbstractCommand command = (AbstractCommand) in.readObject();
        System.out.println("Команда: "+command.getName()+", описание: "+command.getDescription());
    }

    public void downloadText() throws IOException {
        BufferedReader inputText = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(inputText.readLine());
    }
}
