package tests;

import commands.UpdateByID;
import java.io.IOException;

public class testUpdateByID extends AbstractTest{

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        UpdateByID test = new UpdateByID("update {id}", "обновить значение элемента коллекции, id которого равен заданному", getManager());
        test.exec("3");
        setCollection(getManager().getMoviesCollection());
        OutputCollection();
    }
}
