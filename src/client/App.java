package client;

import utility.Asker;
import utility.CommandManager;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        int port = Asker.askPort();
        Client client = new Client("localhost", port);
        CommandManager commandManager = new CommandManager(client);
        try {
            Console.run(commandManager);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
