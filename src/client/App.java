package client;

import client.util.Asker;
import client.util.CommandManager;
import java.io.IOException;
import java.rmi.server.ExportException;

//@author Kyoto67
//@variant 3130

public class App {
    /**
     * client.Main-класс, приветственное сообщение, инициализация менеджера команд, запуск цикла запроса команд от пользователя,
     * передача команд CommandManager'у.
     * @see CommandManager
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        boolean success = false;
        Client client = null;
        while(!(success)){
            try {
                client = new Client("localhost", Asker.askPort());
                success = true;
            } catch (Exception e){
                System.out.println("Не удалось создать соединение.");
                System.out.println(e.getMessage());
            }
        }
        CommandManager commandManager = new CommandManager(client);
        System.out.println("Команды вводятся в формате: \n\"команда\" \"данные\" (через пробел)\nДля просмотра справки введите help.");
        String input = "hello";
        while (!(input.equals("exit"))){
            input=Asker.askCommand();
            commandManager.managerWork(input);
        }
    }

}