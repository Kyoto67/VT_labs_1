package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.exceptions.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * класс, взаимодействующий с файлами
 */
public class FileManager {
    /**
     * остальные поля нужны для сборки коллекции из данных, полученных с файла
     */
    private static PriorityBlockingQueue<Movie> MoviesCollection;
    private static Person director;
    private static Coordinates coordinates;
    private static Movie movie;
    private static Location location;
    private static Date creationDate;
    private static Person rawDirector;
    private static Coordinates rawCoordinates;
    private static Location rawLocation;
    private static Movie rawMovie;
    private static String directorname;
    private static Double directorheight;
    private static Color directoreyeColor;
    private static Color directorhairColor;
    private static Country directornationality;
    private static Double location_x;
    private static Double location_y;
    private static Double location_z;
    private static String location_name;
    private static Double coordinates_x;
    private static Integer coordinates_y;
    private static Long movieid;
    private static String moviename;
    private static Long movieOscarsCount;
    private static MovieGenre movieGenre;
    private static MpaaRating movieMpaaRating;
    private static ArrayList<Long> idList = new ArrayList<>();
    private static final String collectionFileEnv = "LABA";
    private static final String scriptEnv = "LABASCRIPT";

    /**
     * Метод читает коллекцию из файла
     *
     * @return Возвращает готовую коллекцию типа PriorityBlockingQueue<Movie>
     * @throws IOException
     */
    public static PriorityBlockingQueue<Movie> readCollection() {
        boolean read = false;
        while (!read) {
            try {
                Scanner scanner = scan(System.getenv().get(collectionFileEnv));
//                Scanner scanner = scan("/home/kyoto/Collection.json");
                read = true;
                scanner.next(); //первая "{"
                while (scanner.hasNext()) {
                    String line = scanner.next();
                    Parser.parseJSON(line);
                }
            } catch (AccessDeniedException e) {
                System.out.println("Отсутствуют права доступа к файлу коллекции. Обновите права или измените переменную" +
                        " окружения \"LABA\" на другой файл и перезапустите приложение. В память загружена пустая коллекция.");
                return new PriorityBlockingQueue<>();
            } catch (NullPointerException e) {
                System.out.println("Невозможно прочитать коллекцию из заданного файла. Задайте новый и перезапустите приложение. В память загружена" +
                        "пустая коллекция.");
                return new PriorityBlockingQueue<>();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("WARNING! У вас пустая коллекция в файле.");
                return new PriorityBlockingQueue<>();
            } catch (Exception e) {
                System.out.println("Ваш файлик не JSON.... Загружена пустая коллекция.");
                return new PriorityBlockingQueue<>();
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
        if (OS.indexOf("win") >= 0) {
            scan = scan(System.getenv().get(scriptEnv) + "\\" + filename);
        } else {
            scan = scan(System.getenv().get(scriptEnv) + "/" + filename);
//            scan = scan("/home/kyoto" + "/" + filename);
        }
        return scan;
}

    /**
     * Метод сохраняет имеющуюся коллекцию в файл
     *
     * @param collection принимает коллекцию, которую нужно сохранить
     * @throws IOException
     */
    public static void saveCollection(PriorityBlockingQueue<Movie> collection) throws IOException {
        String data = Parser.parseToJSON(collection);
        byte[] buffer = data.getBytes();
        FileOutputStream fos = new FileOutputStream(System.getenv().get(collectionFileEnv));
        fos.write(buffer);
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
    private static void parseJSON(String line) {
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
                MoviesCollection = new PriorityBlockingQueue<>();
                clearAll();
                break;
            }
            case (2): {
                if (elements.get(0).equals("{")) {
                    rawMovie = new Movie();
                } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("coordinates")) {
                    rawCoordinates = new Coordinates();
                } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("director")) {
                    rawDirector = new Person();
                } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("location")) {
                    rawLocation = new Location();
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
                    if (data.equals("null")) {
                        movieid = null;
                    } else {
                        try {
                            movieid = Long.parseLong(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый id, будет null.");
                        }
                    }
                } else if (argument.equals("moviename")) {
                    if (data.equals("null")) {
                        moviename = null;
                    } else {
                        moviename = data;
                    }
                } else if (argument.equals("oscarsCount")) {
                    if (data.equals("null")) {
                        movieOscarsCount = null;
                    } else {
                        try {
                            movieOscarsCount = Long.parseLong(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый oscarscount, будет null.");
                        }
                    }
                } else if (argument.equals("genre")) {
                    if (data.equals("null")) {
                        movieGenre = null;
                    } else {
                        try {
                            movieGenre = MovieGenre.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый MovieGenre, будет null.");
                        }
                    }
                } else if (argument.equals(("mpaaRating"))) {
                    if (data.equals("null")) {
                        movieMpaaRating = null;
                    } else {
                        try {
                            movieMpaaRating = MpaaRating.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый Mpaa Rating, будет null.");
                        }
                    }
                } else if (argument.equals("coordinates_x")) {
                    if (data.equals("null")) {
                        coordinates_x = null;
                    } else {
                        try {
                            coordinates_x = Double.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый coordinates_x, будет null.");
                        }
                    }
                } else if (argument.equals("coordinates_y")) {
                    if (data.equals("null")) {
                        coordinates_y = null;
                    } else {
                        try {
                            coordinates_y = Integer.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый coordinates_y, будет null.");
                        }
                    }
                } else if (argument.equals("directorname")) {
                    if (data.equals("null")) {
                        directorname = null;
                    } else {
                        directorname = data;
                    }
                } else if (argument.equals("height")) {
                    if (data.equals("null")) {
                        directorheight = null;
                    } else {
                        try {
                            directorheight = Double.parseDouble(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый directorHeight, будет null.");
                        }
                    }
                } else if (argument.equals("eyeColor")) {
                    if (data.equals("null")) {
                        directoreyeColor = null;
                    } else {
                        try {
                            directoreyeColor = Color.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый eyeColor, будет null.");
                        }
                    }
                } else if (argument.equals("hairColor")) {
                    if (data.equals("null")) {
                        directorhairColor = null;
                    } else {
                        try {
                            directorhairColor = Color.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый hairColor, будет null.");
                        }
                    }
                } else if (argument.equals("nationality")) {
                    if (data.equals("null")) {
                        directornationality = null;
                    } else {
                        try {
                            directornationality = Country.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый nationality, будет null.");
                        }
                    }
                } else if (argument.equals("location_x")) {
                    if (data.equals("null")) {
                        location_x = null;
                    } else {
                        try {
                            location_x = Double.parseDouble(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый location_x, будет null");
                        }
                    }
                } else if (argument.equals("location_y")) {
                    if (data.equals("null")) {
                        location_y = null;
                    } else {
                        try {
                            location_y = Double.parseDouble(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый location_y, будет null.");
                        }
                    }
                } else if (argument.equals("location_z")) {
                    if (data.equals("null")) {
                        location_z = null;
                    } else {
                        try {
                            location_z = Double.parseDouble(data);
                        } catch (Exception e) {
                            System.out.println("Нечитаемый location_z, будет null.");
                        }
                    }
                } else if (argument.equals("locationname")) {
                    if (data.equals("null")) {
                        location_name = null;
                    } else {
                        location_name = data;
                    }
                } else if (argument.equals("creationDate")) {
                    if (data.equals("null")) {
                        creationDate = null;
                    } else {
                        try {
                            creationDate = new Date(Long.parseLong(data));
                        } catch (Exception e) {
                            System.out.println("Нечитаемый creationDate, будет null");
                        }
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
    private static String parseToJSON(PriorityBlockingQueue<Movie> collection) {
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
        rawDirector = null;
        rawCoordinates = null;
        rawLocation = null;
        rawMovie = null;
        directorname = null;
        directorheight = null;
        directoreyeColor = null;
        directorhairColor = null;
        directornationality = null;
        location_x = null;
        location_y = null;
        location_z = null;
        location_name = null;
        coordinates_x = null;
        coordinates_y = null;
        movieid = null;
        moviename = null;
        movieOscarsCount = null;
        movieGenre = null;
        movieMpaaRating = null;
    }

    private static void loadObject() {
        try {
            rawLocation.setName(location_name);
            rawLocation.setX(location_x);
            rawLocation.setY(location_y);
            rawLocation.setZ(location_z);
        } catch (IncorrectData e) {
            System.out.println(e.getMessage());
            System.out.println("Location будет null.");
            rawLocation = null;
        }
        location = rawLocation;
        try {
            rawCoordinates.setX(coordinates_x);
            rawCoordinates.setY(coordinates_y);
        } catch (IncorrectData e) {
            System.out.println(e.getMessage());
            System.out.println("Coordinates будет null.");
            rawCoordinates = null;
        }
        coordinates = rawCoordinates;
        try {
            rawDirector.setEyeColor(directoreyeColor);
            rawDirector.setHairColor(directorhairColor);
            rawDirector.setHeight(directorheight);
            rawDirector.setName(directorname);
            rawDirector.setNationality(directornationality);
            rawDirector.setLocation(location);
        } catch (IncorrectData | NonRealisticData e) {
            System.out.println(e.getMessage());
            System.out.println("Director будет null.");
            rawDirector = null;
        }
        director = rawDirector;
        try {
            rawMovie.setId(movieid);
            rawMovie.setName(moviename);
            rawMovie.setCoordinates(coordinates);
            rawMovie.setCreationDate(creationDate);
            rawMovie.setOscarsCount(movieOscarsCount);
            rawMovie.setGenre(movieGenre);
            rawMovie.setMpaaRating(movieMpaaRating);
            rawMovie.setDirector(director);
            movie = rawMovie;
            MoviesCollection.add(movie);
            idList.add(movie.getId());
        } catch (IncorrectData e) {
            System.out.printf(e.getMessage() + " Объект не будет добавлен в коллекцию.");
        } catch (Exception e) {
             
            clearAll();
        }
    }

}

    public static ArrayList<Long> getIdList() {
        return idList;
    }
}
