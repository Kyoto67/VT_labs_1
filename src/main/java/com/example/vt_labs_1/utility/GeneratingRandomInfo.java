package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.data.*;

import java.util.Date;
import java.util.Random;

/**
 * класс, генерирующий случайные поля объекта Movie, собирающий их в объект, а объекты в коллекцию.
 */
public class GeneratingRandomInfo {

    /**
     * Генерирует один Movie-объект со случайным значением всех полей.
     * @return Возвращает рандомный Movie
     */
    public static Movie generateOneObject(){
        return generateMovie();
    }

    /**
     * Генерирует случайный объект типа Person
     * @return Возвращает объект типа Person со случайно сгенерированными полями.
     */
    private static Person generatePerson(){
        return new Person(generateRandomString(),Math.random()*100, Color.fromInt((int) (Math.random()*6+0.5)),
                Color.fromInt((int) (Math.random()*6+0.5)), Country.fromInt((int) (Math.random()*2+0.5)), generateLocation());
    }

    /**
     * Метод генерирует случайную строку
     * @return Возвращает String, содержащую случайный набор букв.
     */
    private static String generateRandomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /**
     * Генерирует случайный объект типа Location
     * @return Возвращает объект Location со случайно сгенерированными полями
     */
    private static Location generateLocation(){
        return new Location(Math.random()*500,Math.random()*300,Math.random()*700,generateRandomString());
    }

    /**
     * Генерирует случайный объект типа Coordinates
     * @return Возвращает объект Coordinates со случайно сгенерированными полями
     */
    private static Coordinates generateCoordinates(){
        return new Coordinates((Math.random()*1100),(int) (Math.random() * 1000));
    }

    /**
     * Метод собирает объект Movie из случайных данных
     * @return Возвращает один объект типа Movie, поля которого заполнены случайными значениями.
     */
    private static Movie generateMovie(){
        return new Movie(0,generateRandomString(), generateCoordinates(), new Date(),
                (long) (Math.random()*10000), MovieGenre.fromInt((int) (Math.random()*2+0.5)),
                MpaaRating.fromInt((int) (Math.random()*3+0.5)), generatePerson());
    }
}
