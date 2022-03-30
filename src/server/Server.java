package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private final int port;
    private ServerSocket serverSocket;

    public Server(int port){
        this.port=port;
    }

    public void serverStart() throws IOException {
        serverSocket=new ServerSocket(port);
    }

}
