package server;

import client.commands.*;
import client.data.Movie;
import server.util.CollectionManager;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static final String[] commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script",
            "exit", "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",
            "print_field_descending_oscars_count"};

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server(1337);
        CollectionManager collectionManager = new CollectionManager();
        String actualCommand = "hello";
        while (!(actualCommand.equals("exit"))) {
            AbstractCommand command = server.downloadCommand();
            actualCommand = command.getName();
            Scanner scanner = new Scanner(actualCommand);
            scanner.useDelimiter("\\s");
            actualCommand = scanner.next();
            switch (chooseCommand(actualCommand)) {
                case (0): {
                    Help help = (Help) command;
                    help.exec("");
                    break;
                }
                case (1): {
                    Info info = (Info) command;
                    info.setCollectionManager(collectionManager);
                    info.exec("");
                    break;
                }
                case (2): {
                    Show show = (Show) command;
                    show.setCollectionManager(collectionManager);
                    show.exec("");
                    break;
                }
                case (3): {
                    Movie newMovie = server.downloadMovie();
                    Add add = (Add) command;
                    add.setCollectionManager(collectionManager);
                    add.exec(newMovie);
                    break;
                }
                case (4): {
                    Movie newMovie = server.downloadMovie();
                    UpdateByID updateByID = (UpdateByID) command;
                    updateByID.setCollectionManager(collectionManager);
                    updateByID.exec(newMovie);
                    break;
                }
                case (5): {
                    String id = server.downloadText();
                    RemoveByID removeByID = (RemoveByID) command;
                    removeByID.setCollectionManager(collectionManager);
                    removeByID.exec(id);
                    break;
                }
                case (6): {
                    Clear clear = (Clear) command;
                    clear.setCollectionManager(collectionManager);
                    clear.exec("");
                    break;
                }
                case (7): {
                    Save save = (Save) command;
                    save.setCollectionManager(collectionManager);
                    save.exec("");
                    break;
                }
                case (8): {
                    String filename = server.downloadText();
                    ExecuteScript executeScript = (ExecuteScript) command;
                    executeScript.setCollectionManager(collectionManager);
                    executeScript.exec(filename);
                    break;
                }
                case (9): {
                    Exit exit = (Exit) command;
                    exit.exec("");
                    break;
                }
                case (10): {
                    Movie newMovie = server.downloadMovie();
                    AddIfMin addIfMin = (AddIfMin) command;
                    addIfMin.setCollectionManager(collectionManager);
                    addIfMin.exec(newMovie);
                    break;
                }
                case (11): {
                    Movie movieForCompare = server.downloadMovie();
                    RemoveGreater removeGreater = (RemoveGreater) command;
                    removeGreater.setCollectionManager(collectionManager);
                    removeGreater.exec(movieForCompare);
                    break;
                }
                case (12): {
                    Movie movieForCompare = server.downloadMovie();
                    RemoveLower removeLower = (RemoveLower) command;
                    removeLower.setCollectionManager(collectionManager);
                    removeLower.exec(movieForCompare);
                    break;
                }
                case (13): {
                    String oscarsCount = server.downloadText();
                    RemoveAllByOscarsCount removeAllByOscarsCount = (RemoveAllByOscarsCount) command;
                    removeAllByOscarsCount.setCollectionManager(collectionManager);
                    removeAllByOscarsCount.exec(oscarsCount);
                    break;
                }
                case (14): {
                    String directorName = server.downloadText();
                    RemoveAnyByDirector removeAnyByDirector = (RemoveAnyByDirector) command;
                    removeAnyByDirector.setCollectionManager(collectionManager);
                    removeAnyByDirector.exec(directorName);
                    break;
                }
                case (15): {
                    PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount = (PrintFieldDescendingOscarsCount) command;
                    printFieldDescendingOscarsCount.setCollectionManager(collectionManager);
                    printFieldDescendingOscarsCount.exec("");
                    break;
                }
                case (-1): {
                    System.out.println("Неизвестная ошибка.");
                    break;
                }
            }
        }
    }


    private static int chooseCommand(String command) {
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return i;
            }
        }
        return -1;
    }

}
