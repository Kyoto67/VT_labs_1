package client;

import client.util.Asker;
import client.util.CommandManager;
import java.io.IOException;

//@author Kyoto67
//@variant 3130

public class Main {
    /**
     * client.Main-класс, приветственное сообщение, инициализация менеджера команд, запуск цикла запроса команд от пользователя,
     * передача команд CommandManager'у.
     * @see CommandManager
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        CommandManager commandManager = new CommandManager(new Client("localhost", 1337));
        System.out.println("Команды вводятся в формате: \n\"команда\" \"данные\" (через пробел)\nДля просмотра справки введите help.");
        String input = "hello";
        while (!(input.equals("exit"))){
            input=Asker.askCommand();
            commandManager.managerWork(input);
        }
    }
}