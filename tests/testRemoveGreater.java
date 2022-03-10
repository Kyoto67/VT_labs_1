package tests;

import commands.RemoveGreater;
import java.io.IOException;

public class testRemoveGreater extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        RemoveGreater command = new RemoveGreater("remove_greater {element} ", "удалить из коллекции все элементы, превышающие заданный", getManager());
        command.exec("2");
        OutputCollection();
    }
}
