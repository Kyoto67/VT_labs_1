package tests;

import commands.RemoveLower;
import java.io.IOException;

public class testRemoveLower extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        RemoveLower command = new RemoveLower("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный", getManager());
        command.exec("2");
        OutputCollection();
    }
}
