package server.util;

import client.commands.*;
import client.commands_for_script.*;
import server.exceptions.IncompleteData;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScriptManager implements Serializable {

    private Clear clear;
    private ExecuteScript executeScript;
    private Info info;
    private PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount;
    private RemoveAllByOscarsCount removeAllByOscarsCount;
    private RemoveAnyByDirector removeAnyByDirector;
    private RemoveByID removeByID;
    private Save save;
    private Show show;
    private Add_for_script add_for_script;
    private AddIfMin_for_script addIfMin_for_script;
    private UpdateByID_for_script updateByID_for_script;
    private RemoveGreater_for_script removeGreater_for_script;
    private RemoveLower_for_script removeLower_for_script;
    private ArrayList<String> ScriptsStack = new ArrayList<>();
    private ArrayList<Scanner> scripts = new ArrayList<>();
    private int scriptcounter = -1;
    private final String[] commands = {"info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script",
            "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",
            "print_field_descending_oscars_count"};
    private CollectionManager collectionManager;

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setClear(Clear clear) {
        this.clear = clear;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setPrintFieldDescendingOscarsCount(PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount) {
        this.printFieldDescendingOscarsCount = printFieldDescendingOscarsCount;
    }

    public void setRemoveAllByOscarsCount(RemoveAllByOscarsCount removeAllByOscarsCount) {
        this.removeAllByOscarsCount = removeAllByOscarsCount;
    }

    public void setRemoveAnyByDirector(RemoveAnyByDirector removeAnyByDirector) {
        this.removeAnyByDirector = removeAnyByDirector;
    }

    public void setRemoveByID(RemoveByID removeByID) {
        this.removeByID = removeByID;
    }

    public void setSave(Save save) {
        this.save = save;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setAdd_for_script(Add_for_script add_for_script) {
        this.add_for_script = add_for_script;
    }

    public void setAddIfMin_for_script(AddIfMin_for_script addIfMin_for_script) {
        this.addIfMin_for_script = addIfMin_for_script;
    }

    public void setUpdateByID_for_script(UpdateByID_for_script updateByID_for_script) {
        this.updateByID_for_script = updateByID_for_script;
    }

    public void setRemoveGreater_for_script(RemoveGreater_for_script removeGreater_for_script) {
        this.removeGreater_for_script = removeGreater_for_script;
    }

    public void setRemoveLower_for_script(RemoveLower_for_script removeLower_for_script) {
        this.removeLower_for_script = removeLower_for_script;
    }

    public void setScripts(ArrayList<Scanner> scripts) {
        this.scripts = scripts;
    }

    public void setScriptcounter(int scriptcounter) {
        this.scriptcounter = scriptcounter;
    }

    public ScriptManager() {

    }

    public void setExecuteScript(ExecuteScript executeScript) {
        this.executeScript = executeScript;
    }

    /**
     * Метод для запуска скрипта. Парсит его и по очереди запускает команды, тащит в себе дополнительные аргументы-строки, если нужны.
     *
     * @param script
     * @throws IOException
     */
    public void managerWorkForScript(Scanner script) throws IOException {
        scripts.add(script);
        while (scripts.get(scriptcounter).hasNext()) {
            String[] data = commandParser(scripts.get(scriptcounter).next());
            switch (chooseCommand(data[0])) {
                case (0): {
                    info.setCollectionManager(collectionManager);
                    info.exec("");
                    break;
                }
                case (1): {
                    show.setCollectionManager(collectionManager);
                    show.exec("");
                    break;
                }
                case (2): {
                    add_for_script.setCollectionManager(collectionManager);
                    add_for_script.exec("");
                    break;
                }
                case (3): {
                    updateByID_for_script.setCollectionManager(collectionManager);
                    updateByID_for_script.exec(data[1]);
                    break;
                }
                case (4): {
                    removeByID.setCollectionManager(collectionManager);
                    removeByID.exec(data[1]);
                    break;
                }
                case (5): {
                    clear.setCollectionManager(collectionManager);
                    clear.exec("");
                    break;
                }
                case (6): {
                    save.setCollectionManager(collectionManager);
                    save.exec("");
                    break;
                }
                case (7): {
                    executeScript.exec(data[1]);
                    break;
                }
                case (8): {
                    add_for_script.setCollectionManager(collectionManager);
                    addIfMin_for_script.exec("");
                    break;
                }
                case (9): {
                    removeGreater_for_script.setCollectionManager(collectionManager);
                    removeGreater_for_script.exec("");
                    break;
                }
                case (10): {
                    removeLower_for_script.setCollectionManager(collectionManager);
                    removeLower_for_script.exec("");
                    break;
                }
                case (11): {
                    removeAllByOscarsCount.setCollectionManager(collectionManager);
                    removeAllByOscarsCount.exec(data[1]);
                    break;
                }
                case (12): {
                    removeAnyByDirector.setCollectionManager(collectionManager);
                    removeAnyByDirector.exec(data[1]);
                    break;
                }
                case (13): {
                    printFieldDescendingOscarsCount.setCollectionManager(collectionManager);
                    printFieldDescendingOscarsCount.exec("");
                    break;
                }
                case (-1): {
                    System.out.println("Команда в скрипте не распознана.");
                    break;
                }
            }
        }
    }

    public boolean checkLoopTry(String newScriptFile) {
        for (String s : ScriptsStack) {
            if (s.equals(newScriptFile)) {
                return true;
            }
        }
        return false;
    }

    public String getNextLineFromScript() throws IncompleteData {
        Scanner scanner = scripts.get(scriptcounter);
        for (String c : commands) {
            if (scanner.hasNext(c)) {
                throw new IncompleteData("Данные объекта неполные.");
            }
        }
        return scanner.next();
    }

    public void rollScriptForNextCommand() {
        boolean rollComplete = false;
        Scanner scanner = scripts.get(scriptcounter);
        while (scanner.hasNext()) {
            for (String c : commands) {
                if (scanner.hasNext(c)) {
                    rollComplete = true;
                    break;
                }
            }
            if (!rollComplete) {
                scanner.next();
            } else {
                break;
            }
        }
    }

    public void scriptscounterIncrement() {
        scriptcounter += 1;
    }

    public void scriptscounterDecrement() {
        scripts.remove(scriptcounter);
        scriptcounter -= 1;
        if (scriptcounter == -1) {
            ScriptsStack.removeAll(ScriptsStack);
        }
    }


    public ArrayList<String> getScriptsStack() {
        return ScriptsStack;
    }

    public void setScriptsStack(ArrayList<String> scriptsStack) {
        ScriptsStack = scriptsStack;
    }

    public void addScriptsStack(String newScriptFile) {
        ScriptsStack.add(newScriptFile);
    }

    public String[] commandParser(String line) {
        try {
            Scanner scanner = new Scanner(line);
            if (!(line.indexOf(" ") == -1)) {
                scanner.useDelimiter("\\s");
                String command = scanner.next();
                String data = "";
                if (scanner.hasNext()) {
                    data = scanner.next();
                }
                return new String[]{command, data};
            } else {
                String commandwodata = scanner.next();
                return new String[]{commandwodata};
            }
        } catch (NoSuchElementException e) {
            return new String[]{"  "};
        }
    }

    private int chooseCommand(String command) {
        System.out.println(command);
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return i;
            }
        }
        return -1;
    }
}
