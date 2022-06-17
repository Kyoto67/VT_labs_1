package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.commands.*;
import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.exceptions.IncompleteData;
import com.example.vt_labs_1.exceptions.IncorrectData;
import com.example.vt_labs_1.exceptions.NonRealisticData;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScriptManager implements Serializable {

    private final Add add;
    private final AddIfMin addIfMin;
    private final Clear clear;
    private final ExecuteScript executeScript;
    private final Info info;
    private final PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount;
    private final RemoveAllByOscarsCount removeAllByOscarsCount;
    private final RemoveAnyByDirector removeAnyByDirector;
    private final RemoveByID removeByID;
    private final RemoveGreater removeGreater;
    private final RemoveLower removeLower;
    private final Show show;
    private final UpdateByID updateByID;
    private final Exit exit;
    private final Help help;
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

    public ScriptManager(Add add, AddIfMin addIfMin, Clear clear, ExecuteScript executeScript, Info info, PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount, RemoveAllByOscarsCount removeAllByOscarsCount, RemoveAnyByDirector removeAnyByDirector, RemoveByID removeByID, RemoveGreater removeGreater, RemoveLower removeLower, Show show, UpdateByID updateByID, Exit exit, Help help) {
        this.add = add;
        this.addIfMin = addIfMin;
        this.clear = clear;
        this.executeScript = executeScript;
        this.info = info;
        this.printFieldDescendingOscarsCount = printFieldDescendingOscarsCount;
        this.removeAllByOscarsCount = removeAllByOscarsCount;
        this.removeAnyByDirector = removeAnyByDirector;
        this.removeByID = removeByID;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.show = show;
        this.updateByID = updateByID;
        this.exit = exit;
        this.help = help;
    }

    /**
     * Метод для запуска скрипта. Парсит его и по очереди запускает команды, тащит в себе дополнительные аргументы-строки, если нужны.
     *
     * @param script
     * @throws IOException
     */
    public void managerWorkForScript(Scanner script) {
        scripts.add(script);
        while (scripts.get(scriptcounter).hasNext()) {
            String[] data = commandParser(scripts.get(scriptcounter).next());
            switch (chooseCommand(data[0])) {
                case (0): {
                    info.setCollectionManager(collectionManager);
                    info.exec();
                    break;
                }
                case (1): {
                    show.setCollectionManager(collectionManager);
                    show.exec();
                    break;
                }
                case (2): {
                    add.setCollectionManager(collectionManager);
                    try {
                        add.setArgument(readObject());
                        add.exec();
                    } catch (IncompleteData e) {
                        Module.addMessage(e.getMessage() + " Skip add.");
                    } catch (Exception e) {
                        Module.addMessage("Unreadable common.data. Skip add.");
                        rollScriptForNextCommand();
                    }
                }
                case (3): {
                    updateByID.setCollectionManager(collectionManager);
                    try {
                        Movie newObject = readObject();
                        newObject.setId(Long.parseLong(data[1]));
                        updateByID.setArgument(newObject);
                        updateByID.exec();
                    } catch (IncompleteData e) {
                        Module.addMessage(e.getMessage() + " Skip update_by_id.");
                    } catch (Exception e) {
                        Module.addMessage("Unreadable data. Skip add.");
                    }
                }
                case (4): {
                    removeByID.setCollectionManager(collectionManager);
                    try {
                        removeByID.setArgument(Long.parseLong(data[1]));
                        removeByID.exec();
                    } catch (Exception e) {
                        Module.addMessage("Нечитаемый id, remove_by_id failed.");
                    }
                    break;
                }
                case (5): {
                    clear.setCollectionManager(collectionManager);
                    clear.exec();
                    break;
                }
                case (6): {
                    Module.addMessage("Команда удалена.");
                    break;
                }
                case (7): {
                    executeScript.setArgument(data[1]);
                    executeScript.exec();
                    break;
                }
                case (8): {
                    addIfMin.setCollectionManager(collectionManager);
                    try {
                        addIfMin.setArgument(readObject());
                        addIfMin.exec();
                    } catch (IncompleteData e) {
                        Module.addMessage(e.getMessage() + " Skip add_if_min.");
                    } catch (Exception e) {
                        Module.addMessage("Unreadable common.data. Skip add_if_min.");
                    }
                    break;
                }
                case (9): {
                    removeGreater.setCollectionManager(collectionManager);
                    try {
                        removeGreater.setArgument(readObject());
                        removeGreater.exec();
                    } catch (IncompleteData e) {
                        Module.addMessage(e.getMessage() + " Skip remove_greater.");
                    } catch (Exception e) {
                        Module.addMessage("Unreadable data. Skip remove_greater.");
                        rollScriptForNextCommand();
                    }
                    break;
                }
                case (10): {
                    removeLower.setCollectionManager(collectionManager);
                    try {
                        removeLower.setArgument(readObject());
                        removeLower.exec();
                    } catch (IncompleteData e) {
                        Module.addMessage(e.getMessage() + " Skip remove_lower.");
                    } catch (Exception e) {
                        Module.addMessage("Unreadable common.data. Skip remove_lower.");
                        rollScriptForNextCommand();
                    }
                    break;
                }
                case (11): {
                    removeAllByOscarsCount.setCollectionManager(collectionManager);
                    try {
                        removeAllByOscarsCount.setArgument(Long.parseLong(data[1]));
                        removeAllByOscarsCount.exec();
                    } catch (Exception e) {
                        Module.addMessage("Unreadable id, remove_all_by_oscars_count failed.");
                    }
                    break;
                }
                case (12): {
                    removeAnyByDirector.setCollectionManager(collectionManager);
                    removeAnyByDirector.setArgument(data[1]);
                    removeAnyByDirector.exec();
                    break;
                }
                case (13): {
                    printFieldDescendingOscarsCount.setCollectionManager(collectionManager);
                    printFieldDescendingOscarsCount.exec();
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
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return i;
            }
        }
        return -1;
    }

    private Movie readObject() throws IncompleteData, IncorrectData, NonRealisticData {
        Movie newMovie = new Movie();
        newMovie.setName(getNextLineFromScript());
        newMovie.setGenre(MovieGenre.valueOf(getNextLineFromScript()));
        newMovie.setMpaaRating(MpaaRating.valueOf(getNextLineFromScript()));
        newMovie.setCoordinates(new Coordinates(Double.parseDouble(getNextLineFromScript()), Integer.parseInt(getNextLineFromScript())));
        newMovie.setCreationDate(new Date());
        newMovie.setOscarsCount(Long.parseLong(getNextLineFromScript()));
        Person director = new Person();
        director.setName(getNextLineFromScript());
        director.setHeight(Double.parseDouble(getNextLineFromScript()));
        director.setEyeColor(Color.valueOf(getNextLineFromScript()));
        director.setHairColor(Color.valueOf(getNextLineFromScript()));
        director.setNationality(Country.valueOf(getNextLineFromScript()));
        director.setLocation(new Location(Double.parseDouble(getNextLineFromScript()), Double.parseDouble(getNextLineFromScript()), Double.parseDouble(getNextLineFromScript()), (getNextLineFromScript())));
        newMovie.setDirector(director);
        return newMovie;
    }
}
