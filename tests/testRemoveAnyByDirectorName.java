package tests;

import commands.RemoveAnyByDirector;
import java.io.IOException;

public class testRemoveAnyByDirectorName extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        RemoveAnyByDirector command = new RemoveAnyByDirector("remove_any_by_director {director}", "удалить из коллекции один элемент, значение поля director которого эквивалентно заданному", getManager());
        command.exec(getManager().findElementByID(1).getDirector().getName());
        OutputCollection();
    }
}
