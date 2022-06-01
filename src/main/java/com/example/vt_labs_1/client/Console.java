package com.example.vt_labs_1.client;

import com.example.vt_labs_1.utility.Asker;
import com.example.vt_labs_1.utility.CommandManager;

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