import util.Asker;
import util.CommandManager;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;

//@author Kyoto67
//@variant 3119

public class Main {
    /**
     * Main-класс, приветственное сообщение, инициализация менеджера команд, запуск цикла запроса команд от пользователя,
     * передача команд CommandManager'у.
     * @see CommandManager
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        CommandManager commandManager = new CommandManager();
        System.out.println("Команды вводятся в формате: \n\"команда\" \"данные\" (через пробел)\nДля просмотра справки введите help.");
        String input = "hello";
        while (!(input.equals("exit"))){
            input=Asker.askCommand();
            commandManager.managerWork(input);
        }
    }
}