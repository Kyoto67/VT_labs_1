package common.commands;

import server.util.CollectionManager;
import server.util.FileManager;
import server.util.ScriptManager;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
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
