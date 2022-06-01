package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.*;
import com.example.vt_labs_1.utility.Module;

import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class ExecuteScript extends AbstractCommand {

    private ScriptManager scriptManager;
    private CollectionManager collectionManager;
    private String argument;
    private User user;


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ExecuteScript(String name, String description) {
        super(name, description);
    }

    public void setScriptManager(ScriptManager scriptManager) {
        this.scriptManager = scriptManager;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean exec()  {
        String filename = argument;
        Scanner script = null;
        try {
            script = FileManager.openScriptFile(filename);
        } catch (NoSuchFileException e) {
            Module.addMessage("Файл не найден, скрипт не был запущен.");
            return false;
        } catch (AccessDeniedException e) {
            Module.addMessage("Недостаточно прав для доступа к файлу, скрипт не был запущен.");
            return false;
        } catch (Exception e) {
            Module.addMessage("Ошибка чтения файла скрипта.");
        }
        if (!scriptManager.checkLoopTry(filename)) {
            scriptManager.addScriptsStack(filename);
            scriptManager.scriptscounterIncrement();
            scriptManager.setCollectionManager(collectionManager);
            scriptManager.managerWorkForScript(script);
            scriptManager.scriptscounterDecrement();
        } else {
            Module.addMessage("Обнаружена попытка залупливания. Цикл прерван, скрипт завершён.");
        }
        return true;
    }
}
