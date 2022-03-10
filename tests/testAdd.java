package tests;

import commands.Add;
import java.io.IOException;

public class testAdd extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        Add add = new Add("add {element}", "добавить новый элемент в коллекцию", getManager());
        add.exec("6");
        OutputCollection();
    }
}
