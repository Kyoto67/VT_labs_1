package tests;

import util.FileManager;

import java.io.IOException;

public class testFileManager extends AbstractTest{
    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        FileManager.saveCollectionForTests(getManager().getMoviesCollection(),"test.json");
        setCollection(FileManager.readCollection("test.json"));
        OutputCollection();
    }
}
