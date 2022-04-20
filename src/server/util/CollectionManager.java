package server.util;

import common.data.Movie;
import server.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * класс, объект которого хранит в себе коллекцию и управляет ей.
 */

public class CollectionManager {

    private PriorityQueue<Movie> MoviesCollection = new PriorityQueue<>(idComparator);
    private final String type;
    private final LocalDateTime initTime;
    private ArrayList<Long> idList;

    /**
     * Конструктор класса. Автоматически создаёт коллекцию, заполненную рандомными значениями, сохраняет её в файл и загружает из файла (потому что так сказано в задании).
     * Записывает idList, в котором в реальном времени отображается наличие элементов по их IDшникам.
     * Записывает время инициализации коллекции и её тип.
     *
     * @throws IOException
     */
    public CollectionManager() throws IOException {
        loadCollection();
        idList = FileManager.getIdList();
        type = "PriorityQueue";
        initTime = LocalDateTime.now();
    }

    /**
     * Метод обращается к Файл-Менеджеру, тот читает коллекцию и передаёт её методу, метод записывает полученную коллекцию в своё поле.
     *
     * @throws IOException
     * @see FileManager#readCollection()
     */
    public void loadCollection() throws IOException {
        MoviesCollection = FileManager.readCollection();
    }

    /**
     * Метод выполняет замену элемента с указанным id на новый (с тем же id)
     *
     * @param newMovie объект Movie, который необходимо добавить в коллекцию на замену старому.
     * @param id       id элемента, который нужно заменить
     */
    public void replaceElementByID(Movie newMovie, long id) {
        MoviesCollection.remove(findElementByID(id));
        MoviesCollection.add(newMovie);
    }

    /**
     * Метод добавляет объект в коллекцию, предварительно устанавливая ему id минимальный из тех, что не будет повторён.
     *
     * @param movie полуает на вход объект типа Movie, который необходимо добавить
     */
    public String addElement(Movie movie) {
        movie.setId(addID());
        movie.setCreationDate(new Date());
        MoviesCollection.add(movie);
        return "Добавление успешно.";
    }

    /**
     * Метод удаляет объект из коллекции и удаляет из idList ID удалённого элемента.
     *
     * @param movie объект, который нужно удалить из коллекции.
     */
    public void removeElement(Movie movie) {
        removeID(movie);
        MoviesCollection.remove(movie);
    }

    /**
     * Метод удаляет элемент из коллекции по указанному ID
     *
     * @param id ID объекта, который нужно удалить
     */
    public void removeElementByID(long id) {
        removeElement(findElementByID(id));
    }

    /**
     * Метод перебирает элементы коллекции и ищет соответствие по ID
     *
     * @param id ID элемента, который нужно найти
     * @return Возвращает объект типа Movie, которому соответствует искомый ID. Если элемент не найден, вернёт null
     */
    public Movie findElementByID(long id) {
        if(MoviesCollection.stream().anyMatch((m) -> m.getId() == id)){
            return MoviesCollection.stream().filter((m) -> m.getId() == id).findFirst().get();
        }
        return null;
    }

    /**
     * @return геттер на коллекцию
     */
    public PriorityQueue<Movie> getMoviesCollection() {
        return MoviesCollection;
    }

    /**
     * Метод удаляет все элементы в коллекции и все элементы в idList
     */
    public void removeAllElements() {
        idList.removeAll(idList);
        MoviesCollection.removeAll(MoviesCollection);
    }

    /**
     * Метод генерирует имя файла для сохранения, передаёт его и имеющуюся коллекцию Файл-Менеджеру
     *
     * @throws IOException
     * @see FileManager#saveCollection(PriorityQueue)
     */
    public String saveCollection() throws IOException {
        boolean save = false;
        while (!save) {
            try {
                FileManager.saveCollection(MoviesCollection);
                save = true;
                return "Коллекция сохранена";
            } catch (FileNotFoundException e) {
                return "Отсутствуют права доступа к файлу для записи коллекции. Обновите права или измените" +
                        " переменную окружение \"LABA\" на другой файл и введите команду save на сервере";
            }
        }
        return "Непредвиденная ошибка при сохранении файла";
    }

    /**
     * Метод выводит по-очерёдно строковое представление объектов коллекции.
     */
    public String printCollection() {
        final String[] output = {""};
        MoviesCollection.stream().forEach((M) -> output[0] +=M.toString()+"\n");
        return output[0];
    }

    /**
     * Метод находит один элемент в коллекции по числу oscarsCount
     *
     * @param count число, в соответствие которому нужно найти объект коллекции
     * @return Возвращает найденный объект типа Movie, если объект по заданному номеру не найден, вернёт null
     */
    public Movie findElementByOscarsCount(long count) {
        Long Count = count;
        if(MoviesCollection.stream().anyMatch((M) -> Count.equals(M.getOscarsCount()))){
            return MoviesCollection.stream().filter((M) -> Count==M.getOscarsCount()).findFirst().get();
        }
        return null;
    }

    /**
     * Запускает цикл, пока может найти элемент по заданному oscarsCount, он его удаляет из коллекции.
     *
     * @param count число oscarsCount, по которому нужно удалить объект
     */
    public String removeByOscarsCount(long count) {
        int counter=0;
        while (!(findElementByOscarsCount(count) == null)) {
            removeElement(findElementByOscarsCount(count));
            counter+=1;
        }
        return "Удалено "+counter+" элементов.";
    }

    /**
     * перебирает idList, сравнивает с ID переданного объекта. Если находит соответствие, то удаляет id из idList
     *
     * @param m объект, id которого нужно удалить
     */
    public void removeID(Movie m) {
        idList.stream().filter((ID) -> ID.equals(m.getId())).forEach((ID) -> idList.remove(ID));
    }

    /**
     * Метод ищет минимальное положительное значение id, которое не встречается в idList, добавляет его
     *
     * @return и возвращает добавленное значение
     */
    public long addID() {
        long id = 1;
        while (id > 0) {
            if (!(checkMatchingID(id))) {
                idList.add(id);
                break;
            } else {
                id += 1;
            }
        }
        return id;
    }

    /**
     * Метод считывает все значения oscarsCount из объектов коллекции и записывает их в longList, сортирует и выводит в порядке убывания.
     */
    public String printAllDescendingOscarsCount() {
        final String[] output = {""};
        ArrayList<Long> longList = new ArrayList<>();
        MoviesCollection.stream().forEach((M) -> longList.add(M.getOscarsCount()));
        longList.stream().sorted((o1, o2) -> (int) (o2-o1)).forEach((o) -> output[0] +=o+"\n");
        return output[0];
    }

    /**
     * Перебирает элементы коллекции, находит один элемент по заданному имени режиссёра
     *
     * @param directorName имя режиссёра, по которому нужно найти объект
     * @return Возвращает объект, найденный по имени режиссёра. Если объект не найден, вернёт null
     */
    public Movie findElementByDirector(String directorName) {
        if(MoviesCollection.stream().anyMatch((Movie) -> Movie.getDirector().getName().equals(directorName))){
            return MoviesCollection.stream().filter((Movie) -> Movie.getDirector().getName().equals(directorName)).findFirst().get();
        }
        return null;
    }

    /**
     * находит и удаляет один элемент коллекции, directorName которого равен заданному
     *
     * @param directorName
     */
    public String removeElementByDirectorName(String directorName) {
        removeElement(findElementByDirector(directorName));
        return "Элемент удалён.";
    }
    /**
     * Метод удаляет все элементы из коллекции, чей hashcode меньше заданного.
     *
     * @param movie заданный объект, с хэшкодом которого сравниваются все объекты коллекции
     */
    public String removeAllLower(Movie movie) {
        final String[] output = {"Hashcode введённого объекта: " + movie.hashCode() + ". "};
        MoviesCollection.stream().filter((Movie) -> Movie.hashCode()<movie.hashCode()).forEach((Movie) -> {
            output[0] +="Удаляю объект с hashcode " + Movie.hashCode(); removeElement(Movie);
        });
        return output[0];
    }

    /**
     * Метод удаляет все элементы из коллекции, чей hashcode больше заданного.
     *
     * @param movie заданный movie с хэшкодом которого сравниваются объекты.
     */
    public String removeAllGreater(Movie movie) {
        final String[] output = {"Hashcode введённого объекта: " + movie.hashCode() + ". \n"};
        MoviesCollection.stream().filter((Movie) -> Movie.hashCode()>movie.hashCode()).forEach((Movie) -> {
            output[0] += "Удаляю объект с hashcode " + Movie.hashCode(); removeElement(Movie);
        });
        return output[0];
    }

    /**
     * Метод выполняет сортировку по возрастанию idList и
     *
     * @return Возвращает 0й элемент (минимальный)
     */
    public long getMinElement() {
        final int[] minEl = {999999999};
        MoviesCollection.stream().forEach((m) -> {
            if (m.hashCode() < minEl[0]) {
                minEl[0] = m.hashCode();
            }
        });
        return minEl[0];
    }


    /**
     * Метод выполняет проверку, меньше ли заданный id минимального из имеющихся id элементов в коллекции. Если да, то добавляет заданный объект в коллекцию.
     *
     * @param movie объект, который нужно добавить.
     */
    public String addElementIfLowerMin(Movie movie) {
        String output = "Hashcode введённого объекта: " + movie.hashCode() + ". \n";
        if (movie.hashCode() < getMinElement()) {
            addElement(movie);
            output+="Добавлен новый объект.";
        } else {
            output+= "Объект не был добавлен.";
        }
        return output;
    }

    /**
     * Метод выводит информацию о коллекции: тип, дата инициализации, количество элементов и выполняет перебор всех элементов с выводом их номера ID
     */
    public String printInfo() {
        final String[] output = {"Тип коллекции: " + type + ". \n" + "Дата инициализации: " + initTime + ". \n" + "Количество элементов: "
                + MoviesCollection.size() + ". \n" + "Элементы коллекции по хэшкодам: "};
        MoviesCollection.stream().forEach((m) -> output[0] +=m.hashCode() + " ");
        return output[0];
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным ID в коллекции
     *
     * @param id заданный ID
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingID(long id) {
        return MoviesCollection.stream().anyMatch((Movie) -> Movie.getId() == id);
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным directorName в коллекции
     *
     * @param directorName заданный directorName
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingDirectorName(String directorName) {
        return MoviesCollection.stream().anyMatch((Movie) -> Movie.getDirector().getName().equals(directorName));
    }

    public static Comparator<Movie> idComparator = new Comparator<Movie>() {

        @Override
        public int compare(Movie c1, Movie c2) {
            return (int) (c1.getId() - c2.getId());
        }
    };
}


//            } else if (c1.getId() < c2.getId("Maks, you are sunshine!!!!!!!!!")) {