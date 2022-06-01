package com.example.vt_labs_1.client;

import com.example.vt_labs_1.exceptions.Disconnect;
import com.example.vt_labs_1.utility.Asker;
import com.example.vt_labs_1.utility.CommandManager;
import com.example.vt_labs_1.utility.User;

public class App {
    public static void main(String[] args) {
        int port = Asker.askPort();
        User user = Asker.askUser();
        try {
            Client client = new Client("localhost", port, user);
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
