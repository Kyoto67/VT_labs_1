package server.util;

import client.data.*;
import server.exceptions.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * класс, взаимодействующий с файлами
 */
public class FileManager {
    /**
     * остальные поля нужны для сборки коллекции из данных, полученных с файла
     */
    private static PriorityQueue<Movie> MoviesCollection;
    private static Person director = null;
    private static Coordinates coordinates = null;
    private static Movie movie = null;
    private static Location location = null;
    private static Date creationDate = null;
    private static ArrayList<Long> idList = new ArrayList<>();
    private static final String collectionFileEnv = "LABA";
    private static final String scriptEnv = "LABASCRIPT";

    /**
     * Метод читает коллекцию из файла
     *
     * @return Возвращает готовую коллекцию типа PriorityQueue<Movie>
     * @throws IOException
     */
    public static PriorityQueue<Movie> readCollection() throws IOException {
        boolean read = false;
        while (!read) {
            try {
                //Scanner scanner = scan(System.getenv().get(collectionFileEnv));
                Scanner scanner = scan("/home/kyoto/git/VT_labs_1/Collection.json");
                read = true;
                scanner.next(); //первая "{"
                while (scanner.hasNext()) {
                    try {
                        String line = scanner.next();
                        Parser.parseJSON(line);
                    } catch (readMovieDataException e){
                        while (!Parser.skipMovie(scanner.next())){
                        }
                    } catch (readGenreDataException e){
                        movie.setGenre(null);
                    } catch (readPersonDataException e) {
                        director=null;
                        while (!Parser.skipObject(scanner.next())) {
                        }
                    } catch (readLocationDataException e){
                        location=null;
                        while(!Parser.skipObject(scanner.next())){}
                    } catch (readEyeDataException e){
                        director.setEyeColor(null);
                    } catch (readRatingDataException e){
                        movie.setMpaaRating(null);
                    }
                }
            } catch (AccessDeniedException e) {
                System.out.println("Отсутствуют права доступа к файлу коллекции. Обновите права или измените переменную" +
                        " окружения \"LABA\" на другой файл. После исправления ошибки введите ENTER.");
                System.in.read();
            } catch (Exception e) {
                System.out.println("Ошибка чтения файла с переменной окружения. Задайте новую и нажмите ENTER.");
                System.in.read();
            }
        }
        return MoviesCollection;
    }

    /**
     * Метод читает скрипт из файла
     *
     * @return Возвращает объект типа Scanner, в котором содержится прочитанный скрипт для последующего парсинга
     * @throws IOException
     */
    public static Scanner openScriptFile(String filename) throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        Scanner scan = null;
        boolean read = false;
        while (!read) {
            try {
                if (OS.indexOf("win") >= 0) {
                    //scan = scan(System.getenv().get(scriptEnv) + "\\" + filename);
                    scan = scan("/home/kyoto/git/VT_labs_1/"+filename);
                } else {
                    //scan = scan(System.getenv().get(scriptEnv) + "/" + filename);
                    scan = scan("/home/kyoto/git/VT_labs_1/"+filename);
                }
                read = true;
            } catch (AccessDeniedException e) {
                System.out.println("Отсутствуют права доступа к файлу скрипта. Обновите права или переменную окруженя" +
                        " \"LABASCRIPT\", содержащую директорию со скриптом. После исправления ошибки введите ENTER.");
                System.in.read();
            } catch (Exception e) {
                System.out.println("Ошибка чтения файла с переменной окружения. Задайте новую и нажмите ENTER.");
                System.in.read();
            }
        }
        return scan;
    }

    /**
     * Метод сохраняет имеющуюся коллекцию в файл
     *
     * @param collection принимает коллекцию, которую нужно сохранить
     * @throws IOException
     */
    public static void saveCollection(PriorityQueue<Movie> collection) throws IOException {
        String data = Parser.parseToJSON(collection);
        byte[] buffer = data.getBytes();
        boolean save = false;
        while (!save) {
            try {
                //FileOutputStream fos = new FileOutputStream(System.getenv().get(collectionFileEnv));
                FileOutputStream fos = new FileOutputStream("/home/kyoto/git/VT_labs_1/Collection.json");
                fos.write(buffer);
                save = true;
            } catch (FileNotFoundException e) {
                System.out.println("Отсутствуют права доступа к файлу для записи коллекции. Обновите права или измените" +
                        " переменную окружение \"LABA\" на другой файл. После обновления ошибки введите ENTER.");
                System.in.read();
            }
        }
    }

    /**
     * вспомогательный метод, разбивает полученный файл на строки
     *
     * @param filepath полный путь к файлу, который нужно прочитать
     * @return возвращает объект типа Scanner с разделением считываемого файла на строки
     * @throws IOException
     */
    private static Scanner scan(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        Scanner scanner = new Scanner(path);
        scanner.useDelimiter(System.getProperty("line.separator"));
        return scanner;
    }

    /**
     * Внутренний класс для парсинга формата JSON'а и конвертации в JSON
     */
    private static class Parser {
        /**
         * Метод разбирает строку на элементы и записывает полученные данные (заполняет поля, создаёт объекты, добавляет объекты в коллекцию)
         *
         * @param line получает на вход строку с данными в формате JSON
         */
        private static void parseJSON(String line) throws readMovieDataException, readGenreDataException, readRatingDataException, readPersonDataException, readEyeDataException, readLocationDataException {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter("\\s");
            ArrayList<String> elements = new ArrayList<>();
            while (scanner.hasNext()) {  //разделение строки на элементы MovieCollection
                String element = scanner.next();
                if (!(element.equals(""))) {
                    elements.add(element);
                }
            }
            int caser = whatNeedToDo(elements);
            switch (caser) {
                case (1): {
                    MoviesCollection = new PriorityQueue<>(CollectionManager.idComparator);
                    break;
                }
                case (2): {
                    if (elements.get(0).equals("{")) {
                        movie = new Movie();
                    } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("coordinates")) {
                        coordinates = new Coordinates();
                    } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("director")) {
                        director = new Person();
                    } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("location")) {
                        location = new Location();
                    }
                    break;
                }
                case (3): {
                    loadObject();
                    break;
                }
                case (4): {
                    String argument = elements.get(0).substring(1, elements.get(0).length() - 2);
                    String data = elements.get(1).substring(1, elements.get(1).length() - 1);
                    if (argument.equals("movieid")) {
                        try {
                            movie.setId(Long.parseLong(data));
                            idList.add(Long.parseLong(data));
                        } catch (Exception e) {
                            throw new readMovieDataException("Нечитаемый id.");
                        }
                    } else if (argument.equals("moviename")) {
                        try {
                            movie.setName(data);
                        } catch (Exception e){
                            throw new readMovieDataException("Нечитаемый moviename.");
                        }
                    } else if (argument.equals("oscarsCount")) {
                        try {
                            movie.setOscarsCount(Long.parseLong(data));
                        } catch (Exception e){
                            throw new readMovieDataException("Нечитаемый oscarscount.");
                        }
                    } else if (argument.equals("genre")) {
                        try{
                            movie.setGenre(MovieGenre.valueOf(data));
                        } catch (Exception e){
                            throw new readGenreDataException("Нечитаемый genre.");
                        }
                    } else if (argument.equals(("mpaaRating"))) {
                        try{
                            movie.setMpaaRating(MpaaRating.valueOf(data));
                        } catch (Exception e){
                            throw new readRatingDataException("Нечитаемый rating.");
                        }
                    } else if (argument.equals("coordinates_x")) {
                        try{
                            coordinates.setX(Double.valueOf(data));
                        } catch (Exception e){
                            throw new readMovieDataException("Нечитаемый coordinates_x.");
                        }
                    } else if (argument.equals("coordinates_y")) {
                        try{
                            coordinates.setY(Integer.valueOf(data));
                        } catch (Exception e){
                            throw new readMovieDataException("Нечитаемый coordinates_y.");
                        }
                    } else if (argument.equals("directorname")) {
                        try{
                            director.setName(data);
                        } catch (Exception e){
                            throw new readPersonDataException("Нечитаемый directorname.");
                        }
                    } else if (argument.equals("height")) {
                        try{
                            director.setHeight(Double.parseDouble(data));
                            if(director.getHeight()<=0){
                                throw new readPersonDataException("Рост неположительный.");
                            }
                        } catch (Exception e){
                            throw new readPersonDataException("Нечитаемый directorHeight.");
                        }
                    } else if (argument.equals("eyeColor")) {
                        try{
                            director.setEyeColor(Color.valueOf(data));
                        } catch (Exception e){
                            throw new readEyeDataException("Нечитаемый eyeColor.");
                        }
                    } else if (argument.equals("hairColor")) {
                        try{
                            director.setHairColor(Color.valueOf(data));
                        } catch (Exception e){
                            throw new readPersonDataException("Нечитаемый hairColor.");
                        }
                    } else if (argument.equals("nationality")) {
                        try{
                            director.setNationality(Country.valueOf(data));
                        } catch (Exception e){
                            throw new readPersonDataException("Нечитаемый nationality.");
                        }
                    } else if (argument.equals("location_x")) {
                        try{
                            location.setX(Double.parseDouble(data));
                        } catch (Exception e){
                            throw new readLocationDataException("Нечитаемый location_x.");
                        }
                    } else if (argument.equals("location_y")) {
                        try{
                            location.setY(Double.parseDouble(data));
                        } catch (Exception e){
                            throw new readLocationDataException("Нечитаемый location_y.");
                        }
                    } else if (argument.equals("location_z")) {
                        try{
                            location.setZ(Double.parseDouble(data));
                        } catch (Exception e){
                            throw new readLocationDataException("Нечитаемый location_z.");
                        }
                    } else if (argument.equals("locationname")) {
                        try{
                            location.setName(data);
                        } catch (Exception e){
                            throw new readLocationDataException("Нечитаемый locationname.");
                        }
                    } else if (argument.equals("creationDate")) {
                        try{
                            creationDate = new Date(Long.parseLong(data));
                        } catch (Exception e){
                            throw new readMovieDataException("Нечитаемый creationDate.");
                        }
                    }
                    break;
                }
                case (6): {
                    loadObject();
                }
            }
        }

        /**
         * Метод проходит по полученным символам из строки, ищет соответствие со спецсимволами, возвращает номер в зависимости от того, что найдёт
         *
         * @param elements получает на вход ArrayList<String>
         * @return Возвращает int значение для cases.
         */
        private static int whatNeedToDo(ArrayList<String> elements) {
            for (String s : elements) {
                if (!(s.indexOf("[") == -1)) {
                    return 1;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf("{") == -1)) {
                    return 2;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf(":") == -1)) {
                    return 4;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf("},") == -1)) {
                    return 6;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf("]") == -1)) {
                    return 3;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf("}") == -1)) {
                    return 5;
                }
            }
            return 0;
        }

        /**
         * Метод конвертирует коллекцию в текстовый формат JSON
         *
         * @param collection получает на вход коллекцию, которую необходимо сконвертировать
         * @return Возвращает строку String, которая содержит данные коллекции в формате JSON
         */
        private static String parseToJSON(PriorityQueue<Movie> collection) {
            String data = "";
            data += "{\n\tMovieCollection: [";
            for (Movie m : collection) {
                data += "\n\t\t{\n\t\t\"hashCode\": \"" + m.hashCode() + "\"\n\t\t\"movieid\": \"" + m.getId() + "\"\n\t\t\"moviename\": \"" + m.getName() + "\"\n\t\t\"" +
                        "coordinates\": {\n\t\t\t\"coordinates_x\": \"" + m.getCoordinates().getX() + "\"\n\t\t\t\"coordinates_y" +
                        "\": \"" + m.getCoordinates().getY() + "\"\n\t\t\t}\n\t\t\"creationDate\": \"" + m.getCreationDate().getTime() + "\"\n" +
                        "\t\t\"oscarsCount\": \"" + m.getOscarsCount() + "\"\n" +
                        "\t\t\"genre\": \"" + m.getGenre() + "\"\n\t\t\"mpaaRating\": \"" + m.getMpaaRating() + "\"\n";
                if (m.getDirector() == null) {
                    data += "\t\t\"director\": {\n\t\t\t\"null\"\n";
                } else {
                    data += "\t\t\"" +
                            "director\": {\n\t\t\t\"directorname\": \"" + m.getDirector().getName() + "\"\n\t\t\t\"height\": \""
                            + m.getDirector().getHeight() + "\"\n\t\t\t\"eyeColor\": \"" + m.getDirector().getEyeColor() + "\"\n" +
                            "\t\t\t\"hairColor\": \"" + m.getDirector().getHairColor() + "\"\n\t\t\t\"nationality\": \""
                            + m.getDirector().getNationality() + "\"\n\t\t\t\"location\": {\n\t\t\t\t\"";
                    if (m.getDirector().getLocation() == null) {
                        data += "null\"\n\t\t\t\t}\n";
                    } else {
                        data += "location_x\": \""
                                + m.getDirector().getLocation().getX() + "\"\n\t\t\t\t\"location_y\": \"" + m.getDirector().getLocation()
                                .getY() + "\"\n\t\t\t\t\"location_z\": \"" + m.getDirector().getLocation().getZ() + "\"\n\t\t\t\t\"" +
                                "locationname\": \"" + m.getDirector().getLocation().getName() + "\"\n\t\t\t\t}\n";
                    }
                }

                data += "\t\t\t}\n\t\t},";
            }
            data = data.substring(0, data.length() - 1);
            data += "\n\t]\n}";
            return data;
        }

        private static void clearAll() {
            director = null;
            coordinates = null;
            movie = null;
            location = null;
            creationDate = null;
        }

        private static void loadObject() {
            if (movie.getName() != null) {
                movie.setCoordinates(coordinates);
                director.setLocation(location);
                if(director.getName() != null) {
                    movie.setDirector(director);
                }
                movie.setCreationDate(creationDate);
                MoviesCollection.add(movie);
            }
            clearAll();
        }

        private static boolean skipMovie(String line) {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter("\\s");
            ArrayList<String> elements = new ArrayList<>();
            while (scanner.hasNext()) {  //разделение строки на элементы MovieCollection
                String element = scanner.next();
                if (!(element.equals(""))) {
                    elements.add(element);
                }
            }
            int caser = whatNeedToDo(elements);
            return caser==6;
        }

        private static boolean skipObject(String line) {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter("\\s");
            ArrayList<String> elements = new ArrayList<>();
            while (scanner.hasNext()) {  //разделение строки на элементы MovieCollection
                String element = scanner.next();
                if (!(element.equals(""))) {
                    elements.add(element);
                }
            }
            int caser = whatNeedToDo(elements);
            return caser==5;
        }
    }

    public static ArrayList<Long> getIdList() {
        return idList;
    }
}
