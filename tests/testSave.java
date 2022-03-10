package tests;

import commands.Save;
import java.io.IOException;

public class testSave extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        Save command = new Save("save", "сохранить коллекцию в файл", getManager());
        command.exec("test");
    }
}
