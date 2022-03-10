package tests;

import commands.Clear;
import java.io.IOException;

public class testClear extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        Clear command = new Clear("clear", "очистить коллекцию", getManager());
        command.exec("test");
        OutputCollection();
    }
}
