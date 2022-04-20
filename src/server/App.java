package server;

import common.commands.*;
import server.util.CollectionManager;
import java.util.Scanner;

public class App {

    private static Save save;

    public static void main(String[] args) throws Exception {
        Server server = new Server(2022);
        CollectionManager collectionManager = new CollectionManager();
        server.setCollectionManager(collectionManager);
        server.Run();
    }
}
