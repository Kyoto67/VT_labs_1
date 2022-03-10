package util;

import data.*;
import java.util.Date;
import java.util.Scanner;

public class CreateMovieFromString {
        public static Movie create(String line, long id){
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter("\\s*,\\s*");
            String name = scanner.next();
            Coordinates coordinates = new Coordinates(scanner.nextDouble(), scanner.nextInt());
            Date date = new Date();
            long oscarsCount = scanner.nextLong();
            MovieGenre genre = MovieGenre.valueOf(scanner.next());
            MpaaRating mpaaRating = MpaaRating.valueOf(scanner.next());
            Person director = new Person(scanner.next(),scanner.nextDouble(), Color.valueOf(scanner.next()),
                    Color.valueOf(scanner.next()), Country.valueOf(scanner.next()), new Location(scanner.nextDouble(),
                    scanner.nextDouble(), scanner.nextDouble(), scanner.next()));
            return new Movie(id, name,coordinates,date,oscarsCount,genre,mpaaRating,director);
        }
}
