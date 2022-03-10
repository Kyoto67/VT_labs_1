package tests;

import commands.RemoveByID;
import java.io.IOException;

public class testRemoveByID extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();RemoveByID command = new RemoveByID("remove_by_id {id}", "удалить элемент из коллекции по его id", getManager());
        command.exec("0");
        OutputCollection();
    }
}
