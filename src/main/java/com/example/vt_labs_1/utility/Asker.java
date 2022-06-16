package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.exceptions.IncorrectData;
import com.example.vt_labs_1.exceptions.NonRealisticData;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * класс, который запрашивает информацию у пользователя и передаёт её программе.
 */
public class Asker {

    /**
     * Спрашивает у пользователя, хочет он ввести свои данные для объекта коллекции или желает сгенерировать случайные.
     * Запрашивает ввод данных, пока не получит один из двух вариантов ответа.
     * @return Возвращает True, если пользователь хочет ввести данные, или False, если данные будут сгенерированы автоматически.
     */
    public static boolean askRandomMovie() {
        System.out.print("Желаете ввести данные для нового объекта (1) или сгенерировать их автоматически (2)?\t: ");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        while (!(answer.equals("1") || answer.equals("2"))) {
            System.out.println("Неверные данные. Повторите ввод.");
            in = new Scanner(System.in);
            answer = in.nextLine();
        }
        return answer.equals("1");
    }

    /**
     * Запрашивает у пользователя имя объекта Movie
     * запрашивает повторный ввод, если пользователь пытается ввести null
     * @return Возвращает имя в формате String
     */
    public static String askMovieName() {
        System.out.print("Введите имя фильма: ");
        Scanner in = new Scanner((System.in));
        String MovieName = in.nextLine();
        while (MovieName == null || MovieName.equals("")) {
            System.out.println("Поле не может быть пустым. Повторите ввод.");
            in = new Scanner((System.in));
            MovieName = in.nextLine();
        }
        return MovieName;
    }

    public static int askPort(){
        boolean success = false;
        int port = 0;
        System.out.println("Введите порт подключения.");
        while ((!success)){
            try{
                Scanner scanner = new Scanner(System.in);
                port = Integer.parseInt(scanner.nextLine());
                if(!(port>=1000 && port<30000)){
                    throw new RuntimeException("Неверный порт, повторите ввод.");
                }
                success=true;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return port;
    }

    /**
     * Запрашивает у пользователя команду
     * @return Возвращает введённую пользователем строку
     */
    public static String askCommand() {
        String command = "";
        while (command.equals("")) {
            System.out.print("Введите команду: ");
            Scanner in = new Scanner((System.in));
            command = in.nextLine();
        }
        return command;
    }

    /**
     * Запрашивает у пользователя целое положительное число oscarsCount у объекта Movie
     * будет запрашивать повторный ввод, пока пользователем не будет введено подходящее число
     * @return Возвращает long число, введённое пользователем
     */
    public static long askOscarsCount() {
        boolean success = false;
        long oscarsCount = 0;
        while (!success) {
            try {
                System.out.print("Введите количество оскаров (целое число больше нуля): ");
                Scanner in = new Scanner(System.in);
                oscarsCount = in.nextLong();
                while (!(oscarsCount > 0)) {
                    System.out.println("Неверный формат данных. Повторите ввод.");
                    in = new Scanner(System.in);
                    oscarsCount = in.nextLong();
                }
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return oscarsCount;
    }

    /**
     * Выводит жанры и запрашивает у пользователя ввод желаемого варианта.
     * Будет запрашивать повторный ввод, пока не получит корректное слово, соответствующее одному из представленных жанров или пустую строку (в таком случае выставит null)
     * @return Возвращает выбранный пользователем жанр формата MovieGenre
     */
    public static MovieGenre askGenre() {
        boolean success = false;
        MovieGenre genre = null;
        while (!success) {
            try {
                System.out.println("Возможные жанры: ACTION, DRAMA, ADVENTURE.");
                System.out.print("Введите желаемый жанр: ");
                Scanner in = new Scanner(System.in);
                String answer = in.nextLine();
                if (answer.equals("")) {
                    genre = null;
                } else {
                    genre = MovieGenre.valueOf(answer);
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return genre;
    }

    /**
     * Выводит доступные рейтинги и запрашивает у пользователя ввод желаемого варианта.
     * Будет запрашивать повторный ввод, пока не получит корректное слово, соответствующее одному из представленных рейтингов или пустую строку (в таком случае выставит null)
     * @return Возвращает выбранный пользователем рейтинг в формате MpaaRating
     */
    public static MpaaRating askRating() {
        boolean success = false;
        MpaaRating mpaaRating = null;
        while (!success) {
            try {
                System.out.println("Возможный возрастной рейтинг: PG, PG_13, R, NC_17");
                System.out.print("Введите желаемый рейтинг: ");
                Scanner in = new Scanner(System.in);
                String answer = in.nextLine();
                if (answer.equals("")) {
                    mpaaRating = null;
                } else {
                    mpaaRating = MpaaRating.valueOf(answer);
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return mpaaRating;
    }

    /**
     * Запрашивает у пользователя число, большее -312, для координаты X объекта Movie.
     * Запрашивает повторный ввод, пока не будут введены корректные данные
     * @return Возвращает полученное от пользователя число в формате Double
     */
    public static Double askCoordinatesX() {
        boolean success = false;
        Double coordinateX = null;
        while (!success) {
            try {
                System.out.print("Введите координату Х (дробное число, большее -312, целую часть отделять через \",\"): ");
                Scanner in = new Scanner(System.in);
                coordinateX = in.nextDouble();
                while ((!(coordinateX > -312)) || (coordinateX == null)) {
                    System.out.println("Неверные данные. Повторите ввод.");
                    in = new Scanner(System.in);
                    coordinateX = in.nextDouble();
                }
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return coordinateX;
    }

    /**
     * Запрашивает у пользователя целое число, большее -901, для координаты Y объекта Movie.
     * Запрашивает повторный ввод, пока не будут введены корректные данные
     * @return Возвращает полученное от пользователя число в формате Integer
     */
    public static Integer askCoordinatesY() {
        boolean success = false;
        Integer coordinateY = null;
        while (!success) {
            try {
                System.out.print("Введите координату Y (целое число, большее -901): ");
                Scanner in = new Scanner(System.in);
                coordinateY = in.nextInt();
                while ((!(coordinateY > -901)) || coordinateY == null) {
                    System.out.println("Неверные данные. Повторите ввод.");
                    in = new Scanner(System.in);
                    coordinateY = in.nextInt();
                }
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return coordinateY;
    }

    /**
     * Спрашивает у пользователя, желает ли он сгенерировать режиссёра или оставить объект null
     * Запрашивает повторный ввод, пока варианты ответа не будут подходить под предложенные.
     * @return Возвращает True, если пользователь хочет добавить режиссёра, False, если значение режиссёра будет null
     */
    public static boolean askPerson() {
        System.out.print("Желаете сгенерировать режиссёра? (Y/N): ");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        while (!((answer.equals("Y")) || (answer.equals("N")))) {
            System.out.println("Неверный формат ответа. Повторите ввод.");
            in = new Scanner(System.in);
            answer = in.nextLine();
        }
        return answer.equals("Y");
    }

    /**
     * Запрашивает у пользователя имя режиссёра.
     * Пока строка пустая, запрашивает повторный ввод.
     * @return Возвращает имя, полученное от пользователя, в формате String
     */
    public static String askDirectorName() {
        System.out.print("Введите имя режиссёра: ");
        Scanner in = new Scanner(System.in);
        String DirectorName = in.nextLine();
        while ((DirectorName == null) || (DirectorName.equals(""))) {
            System.out.println("Неверные данные. Повторите ввод.");
            in = new Scanner(System.in);
            DirectorName = in.nextLine();
        }
        return DirectorName;
    }

    /**
     * Запрашивает у пользователя рост режиссёра (положительное число).
     * Запрашивает повторный ввод, пока не получится распознать введённые данные.
     * @return Возвращает полученное у пользователя значение роста в формате double
     */
    public static double askDirectorHeight() {
        boolean success = false;
        double height = 0.0;
        while (!success) {
            try {
                System.out.print("Введите рост режиссёра (дробное число больше нуля, меньше 230 целую часть разделять через \",\"): ");
                Scanner in = new Scanner(System.in);
                height = in.nextDouble();
                while ((!(height > 0)) || (!(height<230))){
                    System.out.println("Неверный формат данных. Повторите ввод.");
                    in = new Scanner(System.in);
                    height = in.nextDouble();
                }
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return height;
    }

    /**
     * Запрашивает у пользователя цвет глаз режиссёра.
     * Выводит доступные варианты цвета, запрашивает у пользователя повторный ввод, если не удаётся сопоставить введённые пользователем данные с представленными цветами.
     * @return Возвращает перечисляемый тип Color или null
     */
    public static Color askDirectorEyeColor() {
        boolean success = false;
        Color eyeColor = null;
        while (!success) {
            try {
                System.out.println("Возможные цвета: BLUE, ORANGE, WHITE, BROWN, GREEN, BLACK, YELLOW. Может быть null (для выбора null введите пустую строку)");
                System.out.print("Выберите желаемый цвет глаз режиссёра: ");
                Scanner in = new Scanner(System.in);
                String s = in.nextLine();
                if (s.equals("")) {
                    eyeColor = null;
                } else {
                    eyeColor = Color.valueOf(s);
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return eyeColor;
    }

    /**
     * Запрашивает у пользователя цвет волос режиссёра.
     * Выводит доступные варианты цвета, запрашивает у пользователя повторный ввод, если не удаётся сопоставить введённые пользователем данные с представленными цветами.
     * @return Возвращает перечисляемый тип Color
     */
    public static Color askDirectorHairColor() {
        boolean success = false;
        Color hairColor = null;
        while (!success) {
            try {
                System.out.println("Возможные цвета: BLUE, ORANGE, WHITE, BROWN, GREEN, BLACK, YELLOW.");
                System.out.print("Выберите желаемый цвет волос режиссёра: ");
                Scanner in = new Scanner(System.in);
                hairColor = Color.valueOf(in.nextLine());
                while (hairColor == null) {
                    System.out.println("Не может быть null. Повторите ввод.");
                    in = new Scanner(System.in);
                    hairColor = Color.valueOf(in.nextLine());
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return hairColor;
    }

    /**
     * Запрашивает у пользователя страну происхождения режиссёра.
     * Выводит доступные варианты стран, запрашивает у пользователя повторный ввод, если не удаётся сопоставить введённые пользователем данные с представленными странами.
     * @return Возвращает перечисляемый тип Country
     */
    public static Country askDirectorCountry() {
        boolean success = false;
        Country nationality = null;
        while (!success) {
            try {
                System.out.println("Возможные страны происхождения: GERMANY, NORTH_KOREA, JAPAN.");
                System.out.print("Выберите желаемую национальность режиссёра: ");
                Scanner in = new Scanner(System.in);
                nationality = Country.valueOf(in.nextLine());
                while (nationality == null) {
                    System.out.println("Не может быть null. Повторите ввод.");
                    in = new Scanner(System.in);
                    nationality = Country.valueOf(in.nextLine());
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return nationality;
    }

    /**
     * Спрашивает у пользователя, желает ли он ввести локацию местонахождения режиссёра или оставить её null
     * Запрашивает повторный ввод, если ответ не подходит под "Y"/"N".
     * @return Возвращает True, если пользователь хочет сгенерировать локацию, False, если выбирает оставить null
     */
    public static boolean askLocation() {
        System.out.print("Желаете сгенерировать локацию режиссёра? (Y/N): ");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        while (!((answer.equals("Y")) || (answer.equals("N")))) {
            System.out.println("Неверный вариант ответа. Повторите ввод.");
            in = new Scanner(System.in);
            answer = in.nextLine();
        }
        return answer.equals("Y");
    }

    /**
     * Спрашивает у пользователя имя локации режиссёра.
     * Запрашивает повторный ввод, если имя больше 233 символов
     * @return Возвращает имя в формате String или null, если была введена пустая строка
     */
    public static String askLocationName() {
        System.out.print("Введите имя локации режиссёра (не больше 233 символов): ");
        Scanner in = new Scanner(System.in);
        String LocationName = in.nextLine();
        while (LocationName.length() > 233) {
            System.out.print("Введите имя короче: ");
            in = new Scanner(System.in);
            LocationName = in.nextLine();
        }
        if (LocationName.equals("")) {
            LocationName = null;
        }
        return LocationName;
    }

    /**
     * Спрашивает у пользователя X-координату локации режиссёра.
     * Запрашивает повторный ввод, если введены нераспознаваемые данные.
     * @return Возвращает полученное у пользователя значение в формате Double
     */
    public static Double askLocationX() {
        boolean success = false;
        Double LocationX = 0.0;
        while (!success) {
            try {
                System.out.print("Введите X-координату локации режиссёра (дробное число, целая часть отделяется через \",\"): ");
                Scanner in = new Scanner(System.in);
                LocationX = in.nextDouble();
                while (LocationX == null) {
                    System.out.print("Поле не может быть null. Повторите ввод: ");
                    in = new Scanner(System.in);
                    LocationX = in.nextDouble();
                }
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return LocationX;
    }

    /**
     * Спрашивает у пользователя Y-координату локации режиссёра.
     * Запрашивает повторный ввод, если введены нераспознаваемые данные.
     * @return Возвращает полученное у пользователя значение в формате double
     */
    public static double askLocationY() {
        boolean success = false;
        double LocationY = 0.0;
        while (!success) {
            try {
                System.out.print("Введите Y-координату локации режиссёра (дробное число, целая часть отделяется через \",\"): ");
                Scanner in = new Scanner(System.in);
                LocationY = in.nextDouble();
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return LocationY;
    }

    /**
     * Спрашивает у пользователя Z-координату локации режиссёра.
     * Запрашивает повторный ввод, если введены нераспознаваемые данные.
     * @return Возвращает полученное у пользователя значение в формате Double
     * @return
     */
    public static Double askLocationZ() {
        boolean success = false;
        Double LocationZ = null;
        while (!success) {
            try {
                System.out.print("Введите Z-координату локации режиссёра (дробное число, целая часть отделяется через \",\"): ");
                Scanner in = new Scanner(System.in);
                LocationZ = in.nextDouble();
                while (LocationZ == null) {
                    System.out.print("Поле не может быть пустым. Повторите ввод: ");
                    in = new Scanner(System.in);
                    LocationZ = in.nextDouble();
                }
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Нераспознаваемые данные, повторите ввод.");
            }
        }
        return LocationZ;
    }

    /**
     * Запрашивает у пользователя номер ID
     * @return Возвращает полученную строку, обработка исключений происходит выше.
     */
    public static String askIDForExec(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Запрашивает у пользователя имя файла со скриптом.
     * @return Возвращает полученную строку, обработка исключений происходит выше.
     */
    public static String askFilenameForExecuteScript(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static Movie askMovie(){
        Movie newMovie = new Movie();
        if (askRandomMovie()){
            try {
                newMovie.setName(askMovieName());
                newMovie.setGenre(askGenre());
                newMovie.setMpaaRating(askRating());
                newMovie.setCoordinates(new Coordinates(askCoordinatesX(), askCoordinatesY()));
                newMovie.setOscarsCount(askOscarsCount());
                if (askPerson()) {
                    Person director = new Person();
                    director.setName(askDirectorName());
                    director.setHeight(askDirectorHeight());
                    director.setEyeColor(askDirectorEyeColor());
                    director.setHairColor(askDirectorHairColor());
                    director.setNationality(askDirectorCountry());
                    if (askLocation()) {
                        director.setLocation(new Location(askLocationX(), askLocationY(), askLocationZ(), askLocationName()));
                    } else {
                        director.setLocation(null);
                    }
                    newMovie.setDirector(director);
                } else {
                    newMovie.setDirector(null);
                }
            } catch (IncorrectData | NonRealisticData e){
                System.out.println(e.getMessage());
                System.out.println("Каким-то чудом вам удалось ввести неправильные данные, объект будет рандомно сгенерирован.");
                return GeneratingRandomInfo.generateOneObject();
            }
        } else {
            newMovie = GeneratingRandomInfo.generateOneObject();
        }
        return newMovie;
    }

//    public static User askUser(){
//        System.out.print("Введите имя пользователя: ");
//        Scanner in = new Scanner(System.in);
//        String username = in.nextLine();
//        System.out.print("Введите пароль: ");
//        String rawPassword = in.nextLine();
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] hashedPass = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
//            String password = hashedPass.toString();
//            return new User(username, password);
//        } catch (NoSuchAlgorithmException ignored){
//            //pass
//        }
//        return null;
//    }
}
