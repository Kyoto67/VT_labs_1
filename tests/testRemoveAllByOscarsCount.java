package tests;

import commands.RemoveAllByOscarsCount;
import java.io.IOException;

public class testRemoveAllByOscarsCount extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();RemoveAllByOscarsCount command = new RemoveAllByOscarsCount("remove_all_by_oscars_count {oscarsCount}", "удалить из коллекции все элементы, значение поля oscarsCount которого эквивалентно заданному", getManager());
        Long OC = getManager().findElementByID(0).getOscarsCount();
        command.exec(OC.toString());
        OutputCollection();
    }
}
