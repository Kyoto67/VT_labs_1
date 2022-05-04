package client;

import exceptions.Disconnect;
import utility.Asker;
import utility.CommandManager;
import utility.DataBaseHandler;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        int port = Asker.askPort();
        try {
            Client client = new Client("localhost", port);
            CommandManager commandManager = new CommandManager(client);
            try {
                Console.run(commandManager);
            } catch (Exception e) {
                 
            }
        } catch (Disconnect e) {
            System.out.println(e.getMessage());
        }
    }
}
