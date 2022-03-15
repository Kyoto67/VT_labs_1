package util;

import data.*;
import exceptions.UnreadableData;

import java.io.FileOutputStream;
import java.io.IOException;
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
     * поле path - системная переменная
     */
    private static final String path = System.getProperty("user.dir");
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

    /**
     * Метод читает коллекцию из файла
     *
     * @param filename полное имя файла, который содержит коллекцию
     * @return Возвращает готовую коллекцию типа PriorityQueue<Movie>
     * @throws IOException
     */
    public static PriorityQueue<Movie> readCollection(String filename) throws IOException {
        Scanner scanner = scan(path + "/" + filename);
        scanner.next(); //первая "{"
        while (scanner.hasNext()) {
            String line = scanner.next();
            Parser.parseJSON(line);
        }
        return MoviesCollection;
    }

    /**
     * Метод читает скрипт из файла
     *
     * @param filename полное имя файла, содержащего скрипт
     * @return Возвращает объект типа Scanner, в котором содержится прочитанный скрипт для последующего парсинга
     * @throws IOException
     */
    public static Scanner openScriptFile(String filename) throws IOException {
        return scan(path + "/" + filename);
    }

    /**
     * Метод сохраняет имеющуюся коллекцию в файл
     *
     * @param collection принимает коллекцию, которую нужно сохранить
     * @param filename   и полное имя файла, в который нужно сохранить
     * @throws IOException
     */
    public static void saveCollection(PriorityQueue<Movie> collection, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(path + "/" + filename);
        String data = Parser.parseToJSON(collection);
        byte[] buffer = data.getBytes();
        fos.write(buffer);
    }

    /**
     * Метод вызывает генерацию рандомной коллекции и записывает её в файл random.json
     *
     * @throws IOException
     */
    public static void createRandomCollection() throws IOException {
        saveCollection(GeneratingRandomInfo.run(), "random.json");
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
                    MoviesCollection = new PriorityQueue<>();
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
                        movie.setId(Long.parseLong(data));
                        idList.add(Long.parseLong(data));
                    } else if (argument.equals("moviename")) {
                        movie.setName(data);
                    } else if (argument.equals("oscarsCount")) {
                        movie.setOscarsCount(Long.valueOf(data));
                    } else if (argument.equals("genre")) {
                        movie.setGenre(MovieGenre.valueOf(data));
                    } else if (argument.equals(("mpaaRating"))) {
                        movie.setMpaaRating(MpaaRating.valueOf(data));
                    } else if (argument.equals("coordinates_x")) {
                        coordinates.setX(Double.valueOf(data));
                    } else if (argument.equals("coordinates_y")) {
                        coordinates.setY(Integer.valueOf(data));
                    } else if (argument.equals("directorname")) {
                        director.setName(data);
                    } else if (argument.equals("height")) {
                        director.setHeight(Double.valueOf(data));
                    } else if (argument.equals("eyeColor")) {
                        director.setEyeColor(Color.valueOf(data));
                    } else if (argument.equals("hairColor")) {
                        director.setHairColor(Color.valueOf(data));
                    } else if (argument.equals("nationality")) {
                        director.setNationality(Country.valueOf(data));
                    } else if (argument.equals("location_x")) {
                        location.setX(Double.valueOf(data));
                    } else if (argument.equals("location_y")) {
                        location.setY(Double.valueOf(data));
                    } else if (argument.equals("location_z")) {
                        location.setZ(Double.valueOf(data));
                    } else if (argument.equals("locationname")) {
                        location.setName(data);
                    } else if (argument.equals("creationDate")) {
                        creationDate = new Date(Long.parseLong(data));
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
            movie.setCoordinates(coordinates);
            director.setLocation(location);
            movie.setDirector(director);
            movie.setCreationDate(creationDate);
            MoviesCollection.add(movie);
            clearAll();
        }
    }

    public static ArrayList<Long> getIdList() {
        return idList;
    }
}
