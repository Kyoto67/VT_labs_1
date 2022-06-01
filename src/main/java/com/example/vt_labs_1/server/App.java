package com.example.vt_labs_1.server;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        while(true) {
            server.run();
        }
//        server.close();
    }
}