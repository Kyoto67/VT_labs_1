package tests;

import commands.PrintFieldDescendingOscarsCount;
import java.io.IOException;

public class testPrintFiledDescendingOscarsCount extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();PrintFieldDescendingOscarsCount command = new PrintFieldDescendingOscarsCount("remove_all_by_oscars_count {oscarsCount}", "удалить из коллекции все элементы, значение поля oscarsCount которого эквивалентно заданному", getManager());
        command.exec("test");
    }
}
