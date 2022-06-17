package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.commands.*;
import com.example.vt_labs_1.exceptions.DatabaseHandlingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Module {

    private static CollectionManager collectionManager;
    private static String outputMessage = "";
    private static DataBaseUserManager dataBaseUserManager;
    private static ArrayList<User> users = new ArrayList<>();
    private static final String[] commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "execute_script",
            "exit", "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",
            "print_field_descending_oscars_count", "connect", "getTable"};


    public static boolean running(AbstractCommand command)  {
        String actualCommand = command.getName();
        Scanner scanner = new Scanner(actualCommand);
        scanner.useDelimiter("\\s");
        actualCommand = scanner.next();
        switch (chooseCommand(actualCommand)) {
            case (0): {
                Help help = (Help) command;
                return (help.exec());
            }
            case (1): {
                Info info = (Info) command;
                info.setCollectionManager(collectionManager);
                return (info.exec());
            }
            case (2): {
                Show show = (Show) command;
                show.setCollectionManager(collectionManager);
                return (show.exec());
            }
            case (3): {
                Add add = (Add) command;
                add.setCollectionManager(collectionManager);
                return (add.exec());
            }
            case (4): {
                UpdateByID updateByID = (UpdateByID) command;
                updateByID.setCollectionManager(collectionManager);
                return (updateByID.exec());
            }
            case (5): {
                RemoveByID removeByID = (RemoveByID) command;
                removeByID.setCollectionManager(collectionManager);
                return (removeByID.exec());
            }
            case (6): {
                Clear clear = (Clear) command;
                clear.setCollectionManager(collectionManager);
                return (clear.exec());
            }
            case (7): {
                ExecuteScript executeScript = (ExecuteScript) command;
                executeScript.setCollectionManager(collectionManager);
                return (executeScript.exec());
            }
            case (8): {
                Exit exit = (Exit) command;
                users.removeIf(u -> u.getUsername().equals(exit.getUser().getUsername()));
                return (exit.exec());
            }
            case (9): {
                AddIfMin addIfMin = (AddIfMin) command;
                addIfMin.setCollectionManager(collectionManager);
                return (addIfMin.exec());
            }
            case (10): {
                RemoveGreater removeGreater = (RemoveGreater) command;
                removeGreater.setCollectionManager(collectionManager);
                return (removeGreater.exec());
            }
            case (11): {
                RemoveLower removeLower = (RemoveLower) command;
                removeLower.setCollectionManager(collectionManager);
                return (removeLower.exec());
            }
            case (12): {
                RemoveAllByOscarsCount removeAllByOscarsCount = (RemoveAllByOscarsCount) command;
                removeAllByOscarsCount.setCollectionManager(collectionManager);
                return (removeAllByOscarsCount.exec());
            }
            case (13): {
                RemoveAnyByDirector removeAnyByDirector = (RemoveAnyByDirector) command;
                removeAnyByDirector.setCollectionManager(collectionManager);
                return (removeAnyByDirector.exec());
            }
            case (14): {
                PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount = (PrintFieldDescendingOscarsCount) command;
                printFieldDescendingOscarsCount.setCollectionManager(collectionManager);
                return (printFieldDescendingOscarsCount.exec());
            }
            case (15): {
                Connect connect = (Connect) command;
                for (User u : users) {
                    if (u.getUsername().equals(connect.getUser().getUsername())) {
                        addMessage("Данный пользователь уже работает с базой данных.");
                        return false;
                    }
                }
                try {
                    if (connect.getUser().isSignup()) {
                        try {
                            dataBaseUserManager.getUserByUsername(connect.getUser().getUsername());
                            addMessage("Пользователь с таким именем уже зарегистрирован.");
                            return false;
                        } catch (SQLException e) {
                            dataBaseUserManager.insertUser(connect.getUser());
                            users.add(connect.getUser());
                            addMessage("Зарегистрирован новый пользователь.");
                            return true;
                        }
                    } else {
                        if (dataBaseUserManager.checkUserByUsernameAndPassword(connect.getUser())) {
                            addMessage("Авторизация успешна.");
                            return true;
                        } else {
                            addMessage("Некорректные данные для входа.");
                            return false;
                        }
                    }
                } catch (DatabaseHandlingException e) {
                    addMessage("Произошла непредвиденная ошибка при авторизации.");
                    return false;
                }
            }
            case (16): {
                getTable table = (getTable) command;
                table.setCollectionManager(collectionManager);
                return table.exec();
            }
            case (-1): {
                System.out.println("Неизвестная ошибка.");
                return (false);
            }
        }
        return false;
    }

    public static String messageFlush() {
        String output = Module.outputMessage;
        Module.outputMessage = "";
        return output;
    }

    public static void addMessage(String s) {
        outputMessage += s + "\n";
    }

    private static int chooseCommand(String command) {
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void setCollectionManager(CollectionManager collectionManager) {
        Module.collectionManager = collectionManager;
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static void setDataBaseUserManager(DataBaseUserManager dataBaseUserManager) {
        Module.dataBaseUserManager = dataBaseUserManager;
    }
}
