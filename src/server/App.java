package server;


import utility.DataBaseHandler;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        while(true) {
            server.run();
        }
//        server.close();
    }
}