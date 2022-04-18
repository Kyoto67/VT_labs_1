package server;

import common.commands.*;
import server.util.CollectionManager;
import java.util.Scanner;

public class App {

    private static Save save;

    public static void main(String[] args) throws Exception {
        Server server = new Server(1337);
        CollectionManager collectionManager = new CollectionManager();
        server.setCollectionManager(collectionManager);
        server.Run();
        System.out.println("Клиентское приложение завершено. Серверу доступны команды:\nsave: Сохранение коллекции в файл\nexit: Закрытие сервера.");
        save = new Save("save", "Сохранение коллекции в файл",collectionManager);
        String actualCommand = "client_off";
        while(!(actualCommand.equals("exit"))){
            Scanner in = new Scanner((System.in));
            actualCommand = in.nextLine();
            if (actualCommand.equals("exit")){
                System.out.println("Пока, пока...");
            } else if (actualCommand.equals("save")) {
                save.exec("");
            } else {
                System.out.println("Некорректная команда.");
            }
        }
    }
}
