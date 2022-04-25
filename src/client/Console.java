package client;

import utility.Asker;
import utility.CommandManager;

public class Console {

    public static void run(CommandManager commandManager) throws Exception {
        System.out.println("Приложение запущено. Для справки введите \"help\"");
        String input = "run";
        while (!(input.equals("exit"))){
            input = Asker.askCommand();
            commandManager.managerWork(input);
            System.out.println("");
        }
    }
}