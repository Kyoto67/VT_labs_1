package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.data.Movie;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * класс, объект которого хранит в себе коллекцию и управляет ей.
 */

public class CollectionManager {

    private PriorityBlockingQueue<Movie> MoviesCollection = new PriorityBlockingQueue<>();
    private final String type;
    private final LocalDateTime initTime;
    private ArrayList<Long> idList;
    private final DataBaseCollectionManager dataBaseCollectionManager;

    /**
     * Конструктор класса. Автоматически создаёт коллекцию, заполненную рандомными значениями, сохраняет её в файл и загружает из файла (потому что так сказано в задании).
     * Записывает idList, в котором в реальном времени отображается наличие элементов по их IDшникам.
     * Записывает время инициализации коллекции и её тип.
     */
    public CollectionManager(DataBaseCollectionManager dataBaseCollectionManager) {
        this.dataBaseCollectionManager = dataBaseCollectionManager;
        loadCollection();
        idList = FileManager.getIdList();
        type = "PriorityBlockingQueue";
        initTime = LocalDateTime.now();
    }

    /**
     * Метод обращается к Файл-Менеджеру, тот читает коллекцию и передаёт её методу, метод записывает полученную коллекцию в своё поле.
     *
     * @throws IOException
     * @see FileManager#readCollection()
     */
    public synchronized void loadCollection() {
        MoviesCollection = (dataBaseCollectionManager.getCollection());
    }

    /**
     * Метод выполняет замену элемента с указанным id на новый (с тем же id)
     *
     * @param newMovie объект Movie, который необходимо добавить в коллекцию на замену старому.
     * @param id       id элемента, который нужно заменить
     */
    public synchronized void replaceElementByID(Movie newMovie, long id, User user) {
        try {
            dataBaseCollectionManager.deleteMovieById(id);
            MoviesCollection.remove(findElementByID(id));
            MoviesCollection.add(dataBaseCollectionManager.insertMovie(newMovie, user));
        } catch (Exception ignore) {
            //pass
        }
    }

    /**
     * Метод добавляет объект в коллекцию, предварительно устанавливая ему id минимальный из тех, что не будет повторён.
     *
     * @param movie полуает на вход объект типа Movie, который необходимо добавить
     */
    public synchronized String addElement(Movie movie, User user) {
        try {
            Movie newMovie = movie;
            newMovie.setCreationDate(new Date());
            newMovie = dataBaseCollectionManager.insertMovie(newMovie, user);
            addID(newMovie);
            MoviesCollection.add(newMovie);
            return "Добавление успешно.";
        } catch (Exception e) {
            return "Произошла неизвестная ошибка при добавлении элемента.";
        }
    }

    /**
     * Метод удаляет объект из коллекции и удаляет из idList ID удалённого элемента.
     *
     * @param movie объект, который нужно удалить из коллекции.
     */
    public synchronized void removeElement(Movie movie) {
        try {
            dataBaseCollectionManager.deleteMovieById(movie.getId());
            removeID(movie);
            MoviesCollection.remove(movie);
        } catch (Exception ignore) {
            Module.addMessage("Произошла неизвестная ошибка при удалении элемента.");
        }
    }

    /**
     * Метод удаляет элемент из коллекции по указанному ID
     *
     * @param id ID объекта, который нужно удалить
     */
    public synchronized void removeElementByID(long id) {
        removeElement(findElementByID(id));
    }

    /**
     * Метод перебирает элементы коллекции и ищет соответствие по ID
     *
     * @param id ID элемента, который нужно найти
     * @return Возвращает объект типа Movie, которому соответствует искомый ID. Если элемент не найден, вернёт null
     */
    public synchronized Movie findElementByID(long id) {
        if (MoviesCollection.stream().anyMatch((m) -> m.getId() == id)) {
            return MoviesCollection.stream().filter((m) -> m.getId() == id).findFirst().get();
        }
        return null;
    }

    /**
     * @return геттер на коллекцию
     */
    public synchronized PriorityBlockingQueue<Movie> getMoviesCollection() {
        return MoviesCollection;
    }

    /**
     * Метод удаляет все элементы в коллекции и все элементы в idList
     */
    public synchronized void removeAllElements(User user) {
        LinkedList<Movie> listForRemove = new LinkedList<>();
        MoviesCollection.stream().filter((Movie) -> Movie.getOwner().getUsername().equals(user.getUsername())).forEach(listForRemove::add);
        listForRemove.forEach(this::removeElement);
    }

    /**
     * Метод генерирует имя файла для сохранения, передаёт его и имеющуюся коллекцию Файл-Менеджеру
     *
     * @throws IOException
     * @see FileManager#saveCollection(PriorityBlockingQueue)
     */
//    public synchronized String saveCollection() throws IOException {
//        try {
//            FileManager.saveCollection(MoviesCollection);
//            return "Коллекция сохранена";
//        } catch (FileNotFoundException e) {
//            return "Отсутствуют права доступа к файлу для записи коллекции. Обновите права или измените" +
//                    " переменную окружение \"LABA\" на другой файл и введите команду save на сервере";
//        } catch (Exception e) {
//            return "Непредвиденная ошибка при сохранении файла";
//        }
//    }

    /**
     * Метод выводит по-очерёдно строковое представление объектов коллекции.
     */
    public synchronized String printCollection() {
        final String[] output = {""};
        try {
            dataBaseCollectionManager.getCollection().stream().forEach((M) -> output[0] += M.toString() + "\n");
        } catch (Exception e) {
        }
        return output[0];
    }

    /**
     * Метод находит один элемент в коллекции по числу oscarsCount
     *
     * @param count число, в соответствие которому нужно найти объект коллекции
     * @return Возвращает найденный объект типа Movie, если объект по заданному номеру не найден, вернёт null
     */
    public synchronized Movie findElementByOscarsCount(long count) {
        Long Count = count;
        if (MoviesCollection.stream().anyMatch((M) -> Count.equals(M.getOscarsCount()))) {
            return MoviesCollection.stream().filter((M) -> Count == M.getOscarsCount()).findFirst().get();
        }
        return null;
    }

    /**
     * Запускает цикл, пока может найти элемент по заданному oscarsCount, он его удаляет из коллекции.
     *
     * @param count число oscarsCount, по которому нужно удалить объект
     */
    public synchronized String removeByOscarsCount(long count, User user) {
        LinkedList<Movie> listForRemove = new LinkedList<>();
        int counter;
        MoviesCollection.stream().filter((Movie) -> Movie.getOwner().getUsername().equals(user.getUsername())).filter((Movie) -> Movie.getOscarsCount() == count).forEach(listForRemove::add);
        counter = listForRemove.size();
        listForRemove.forEach(this::removeElement);
        return "Удалено " + counter + " элементов.";
    }

    /**
     * перебирает idList, сравнивает с ID переданного объекта. Если находит соответствие, то удаляет id из idList
     *
     * @param m объект, id которого нужно удалить
     */
    public synchronized void removeID(Movie m) {
        idList.remove(m.getId());
    }

    /**
     * Метод ищет минимальное положительное значение id, которое не встречается в idList, добавляет его
     *
     * @return и возвращает добавленное значение
     */
    public synchronized void addID(Movie movie) {
        idList.add(movie.getId());
    }

    /**
     * Метод считывает все значения oscarsCount из объектов коллекции и записывает их в longList, сортирует и выводит в порядке убывания.
     */
    public synchronized String printAllDescendingOscarsCount() {
        final String[] output = {""};
        ArrayList<Long> longList = new ArrayList<>();
        MoviesCollection.stream().forEach((M) -> longList.add(M.getOscarsCount()));
        longList.stream().sorted((o1, o2) -> (int) (o2 - o1)).forEach((o) -> output[0] += o + "\n");
        return output[0];
    }

    /**
     * Перебирает элементы коллекции, находит один элемент по заданному имени режиссёра
     *
     * @param directorName имя режиссёра, по которому нужно найти объект
     * @return Возвращает объект, найденный по имени режиссёра. Если объект не найден, вернёт null
     */
    public synchronized Movie findElementByDirector(String directorName) {
        if (MoviesCollection.stream().anyMatch((Movie) -> Movie.getDirector().getName().equals(directorName))) {
            return MoviesCollection.stream().filter((Movie) -> Movie.getDirector().getName().equals(directorName)).findFirst().get();
        }
        return null;
    }

    /**
     * находит и удаляет один элемент коллекции, directorName которого равен заданному
     *
     * @param directorName
     */
    public synchronized String removeElementByDirectorName(String directorName) {
        removeElement(findElementByDirector(directorName));
        return "Элемент удалён.";
    }

    /**
     * Метод удаляет все элементы из коллекции, чей hashcode меньше заданного.
     *
     * @param movie заданный объект, с хэшкодом которого сравниваются все объекты коллекции
     */
    public synchronized String removeAllLower(Movie movie, User user) {
        LinkedList<Movie> listForRemove = new LinkedList<>();
        final String[] output = {"Hashcode введённого объекта: " + movie.hashCode() + ". "};
        MoviesCollection.stream().filter((Movie) -> Movie.getOwner().getUsername().equals(user.getUsername())).filter((Movie) -> Movie.hashCode() < movie.hashCode()).forEach((Movie) -> {
            output[0] += "Удаляю объект с hashcode " + Movie.hashCode();
            listForRemove.add(Movie);
        });
        listForRemove.forEach(this::removeElement);
        return output[0];
    }

    /**
     * Метод удаляет все элементы из коллекции, чей hashcode больше заданного.
     *
     * @param movie заданный movie с хэшкодом которого сравниваются объекты.
     */
    public synchronized String removeAllGreater(Movie movie, User user) {
        LinkedList<Movie> listForRemove = new LinkedList<>();
        final String[] output = {"Hashcode введённого объекта: " + movie.hashCode() + ". \n"};
        MoviesCollection.stream().filter((Movie) -> Movie.getOwner().getUsername().equals(user.getUsername())).filter((Movie) -> Movie.hashCode() > movie.hashCode()).forEach((Movie) -> {
            output[0] += "Удаляю объект с hashcode " + Movie.hashCode();
            listForRemove.add(Movie);
        });
        listForRemove.forEach(this::removeElement);
        return output[0];
    }

    /**
     * Метод выполняет сортировку по возрастанию idList и
     *
     * @return Возвращает 0й элемент (минимальный)
     */
    public synchronized long getMinElement() {
        final int[] minEl = {999999999};
        MoviesCollection.forEach((m) -> {
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
    public synchronized String addElementIfLowerMin(Movie movie, User user) {
        String output = "Hashcode введённого объекта: " + movie.hashCode() + ". \n";
        if (movie.hashCode() < getMinElement()) {
            output += addElement(movie, user);
        } else {
            output += "Объект не был добавлен.";
        }
        return output;
    }

    /**
     * Метод выводит информацию о коллекции: тип, дата инициализации, количество элементов и выполняет перебор всех элементов с выводом их номера ID
     */
    public synchronized String printInfo() {
        final String[] output = {"Тип коллекции: " + type + ". \n" + "Дата инициализации: " + initTime + ". \n" + "Количество элементов: "
                + MoviesCollection.size() + ". \n" + "Элементы коллекции по хэшкодам: "};
        MoviesCollection.stream().forEach((m) -> output[0] += m.hashCode() + " ");
        return output[0];
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным ID в коллекции
     *
     * @param id заданный ID
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public synchronized boolean checkMatchingID(long id, User user) {
        if (MoviesCollection.isEmpty()) {
            return false;
        } else {
            return MoviesCollection.stream().filter((Movie) -> Movie.getOwner().getUsername().equals(user.getUsername())).anyMatch((Movie) -> Movie.getId() == id);
        }
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным directorName в коллекции
     *
     * @param directorName заданный directorName
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public synchronized boolean checkMatchingDirectorName(String directorName, User user) {
        for (Movie m : MoviesCollection) {
            if (m.getDirector().getName().equals(directorName) && m.getOwner().getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public synchronized String getTable() {
        LinkedList<String[]> table = dataBaseCollectionManager.getTable();
        final String[] ans = {""};
        table.forEach(strings -> {
            for (String s : strings) {
                ans[0] += s + "\n";
            }
        });
        return ans[0];
    }

}


//            } else if (c1.getId() < c2.getId("Maks, you are sunshine!!!!!!!!!")) {