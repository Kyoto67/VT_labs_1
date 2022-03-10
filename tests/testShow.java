package tests;

import commands.Show;
import java.io.IOException;

public class testShow extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        Show command = new Show("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", getManager());
        command.exec("test");
    }
}
