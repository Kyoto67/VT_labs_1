package util;

import data.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CollectionLoader extends CreateMovieFromString{
    public static PriorityQueue run(PriorityQueue collection, String filepath) throws IOException {
        Scanner scanner = scan(filepath);
        long id = 0;
        while (scanner.hasNext()) {
            Movie movie = create(scanner.next(), id);
            id++;
            collection.add(movie);
        }
        return collection;
    }

    private static Scanner scan(String filename) throws IOException {
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);
        scanner.useDelimiter(System.getProperty("line.separator"));
        return scanner;
    }


}
