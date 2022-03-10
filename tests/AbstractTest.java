package tests;

import data.Movie;
import util.CollectionManager;
import util.FileManager;
import util.GeneratingRandomInfo;

import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;

public abstract class AbstractTest{

    private static final String testfile = "/test.json";
    private static CollectionManager manager;

    public static void run() throws IOException {};

    protected static void OutputCollection(){
        for (Movie m : manager.getMoviesCollection()){
            System.out.println(m.toString());
        }
    }

    protected void initialization(){

    }

    public static CollectionManager getManager() {
        return manager;
    }

    public static void setManager(CollectionManager manager) {
        AbstractTest.manager = manager;
    }

    protected static void createTestCollection() throws IOException {
        manager = new CollectionManager();
        for (int i=0; i<5; i++) {
            manager.addElement(GeneratingRandomInfo.generateOneObject());
        }
    }

    protected static void setCollection(PriorityQueue<Movie> c){
        manager.loadTestCollection(c);
    }

    protected static String getFilename(){
        return testfile;
    }
}
