package client.commands;
import client.util.Asker;
import client.util.CommandManager;
import server.util.FileManager;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class ExecuteScript extends AbstractCommand{

    private final CommandManager commandManager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param commandManager сущность, которая отвечает за распознавание и вызов выполнения всех команд.
     */
    public ExecuteScript(String name, String description, CommandManager commandManager){
        super(name, description);
        this.commandManager=commandManager;
    }

    /**
     * Метод читает скрипт-файл, при неверно введённом имени файла или отсутствии прав доступа запрашивает повторный ввод имени файла.
     * После прочтения файла последовательно выполняет все команды.
     * @param argument имя файла, содержащего скрипт
     * @return Возвращает True при выполнении команды
     * @throws IOException
     * @see FileManager#openScriptFile(String)
     * @see Asker#askFilenameForExecuteScript()
     * @see CommandManager#managerWork(String)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        boolean success=false;
        String filename=argument;
        Scanner script=null;
        while (!success){
            try {
                script = FileManager.openScriptFile(filename);
                success=true;
            } catch (NoSuchFileException e){
                System.out.println("Файл не найден, повторите ввод имени файла.");
                filename= Asker.askFilenameForExecuteScript();
            } catch (AccessDeniedException e) {
                System.out.println("Недостаточно прав для доступа к файлу, повторите ввод имени файла.");
                filename= Asker.askFilenameForExecuteScript();
            }
        }
        if (!commandManager.checkLoopTry(filename)){
            commandManager.addScriptsStack(filename);
            commandManager.scriptscounterIncrement();
            commandManager.managerWorkForScript(script);
            commandManager.scriptscounterDecrement();
        } else {
            System.out.println("Обнаружена попытка залупливания. Цикл прерван, скрипт завершён.");
        }
        return true;
    }
}
