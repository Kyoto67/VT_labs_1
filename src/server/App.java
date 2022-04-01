package server;

import java.io.*;

class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    Server server = new Server(1337);
    server.downloadCommand();
    }
}
