package client.commands;
import client.commands_for_script.*;
import client.util.Asker;
import client.util.CommandManager;
import server.util.CollectionManager;
import server.util.FileManager;
import server.util.ScriptManager;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExecuteScript extends AbstractCommand{


    private static final long serialVersionUID = 5L;
    private final ScriptManager scriptManager;
    private CollectionManager collectionManager;

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ExecuteScript(String name, String description, ScriptManager scriptManager) {
        super(name, description);
        this.scriptManager=scriptManager;
        scriptManager.setExecuteScript(this);
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
        String filename=argument;
        Scanner script;
        try {
            script = FileManager.openScriptFile(filename);
        } catch (NoSuchFileException e){
                System.out.println("Файл не найден, скрипт не был запущен.");
                return false;
            } catch (AccessDeniedException e) {
                System.out.println("Недостаточно прав для доступа к файлу, скрипт не был запущен.");
                return false;
            }
        if (!scriptManager.checkLoopTry(filename)){
            scriptManager.addScriptsStack(filename);
            scriptManager.scriptscounterIncrement();
            scriptManager.setCollectionManager(collectionManager);
            scriptManager.managerWorkForScript(script);
            scriptManager.scriptscounterDecrement();
        } else {
            System.out.println("Обнаружена попытка залупливания. Цикл прерван, скрипт завершён.");
        }
        return true;
    }
}
