package server.util;

import common.data.Movie;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

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
     * Метод, используемый для тестов, оставлен для возможных тестов в будущем.
     * Загружает определённую коллекцию (обычно небольшую и с заранее забитыми данными).
     *
     * @param moviesCollection
     */
    public void loadTestCollection(PriorityQueue<Movie> moviesCollection) {
        this.MoviesCollection = moviesCollection;
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
        sortCollection();
    }

    /**
     * Метод добавляет объект в коллекцию, предварительно устанавливая ему id минимальный из тех, что не будет повторён.
     *
     * @param movie полуает на вход объект типа Movie, который необходимо добавить
     */
    public void addElement(Movie movie) {
        movie.setId(addID());
        movie.setCreationDate(new Date());
        MoviesCollection.add(movie);
    }

    /**
     * Метод удаляет объект из коллекции и удаляет из idList ID удалённого элемента.
     *
     * @param movie объект, который нужно удалить из коллекции.
     */
    public void removeElement(Movie movie) {
        removeID(movie);
        MoviesCollection.remove(movie);
        sortCollection();
    }

    /**
     * Метод удаляет элемент из коллекции по указанному ID
     *
     * @param id ID объекта, который нужно удалить
     */
    public void removeElementByID(long id) {
        removeElement(findElementByID(id));
        sortCollection();
    }

    /**
     * Метод перебирает элементы коллекции и ищет соответствие по ID
     *
     * @param id ID элемента, который нужно найти
     * @return Возвращает объект типа Movie, которому соответствует искомый ID. Если элемент не найден, вернёт null
     */
    public Movie findElementByID(long id) {
        for (Movie m : MoviesCollection) {
            Long mID = m.getId();
            if (mID.equals(id)) {
                return m;
            }
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
    public void saveCollection() throws IOException {
        FileManager.saveCollection(MoviesCollection);
    }

    /**
     * Метод выводит по-очерёдно строковое представление объектов коллекции.
     */
    public void printCollection() {
        sortCollection();
        for (Movie m : MoviesCollection) {
            System.out.println(m.toString());
        }
    }

    /**
     * Метод находит один элемент в коллекции по числу oscarsCount
     *
     * @param count число, в соответствие которому нужно найти объект коллекции
     * @return Возвращает найденный объект типа Movie, если объект по заданному номеру не найден, вернёт null
     */
    public Movie findElementByOscarsCount(long count) {
        for (Movie m : MoviesCollection) {
            Long OscarsCount = m.getOscarsCount();
            if (OscarsCount.equals(count)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Запускает цикл, пока может найти элемент по заданному oscarsCount, он его удаляет из коллекции.
     *
     * @param count число oscarsCount, по которому нужно удалить объект
     */
    public void removeByOscarsCount(long count) {
        while (!(findElementByOscarsCount(count) == null)) {
            removeElement(findElementByOscarsCount(count));
        }
        sortCollection();
    }

    /**
     * перебирает idList, сравнивает с ID переданного объекта. Если находит соответствие, то удаляет id из idList
     *
     * @param m объект, id которого нужно удалить
     */
    public void removeID(Movie m) {
        for (Long id : idList) {
            if (id.equals(m.getId())) {
                idList.remove(id);
                break;
            }
        }
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
    public void printAllDescendingOscarsCount() {
        ArrayList<Long> longList = new ArrayList<>();
        for (Movie m : MoviesCollection) {
            longList.add(m.getOscarsCount());
        }
        Collections.sort(longList);
        Collections.reverse(longList);
        for (long l : longList) {
            System.out.println(l);
        }
    }

    /**
     * Перебирает элементы коллекции, находит один элемент по заданному имени режиссёра
     *
     * @param directorName имя режиссёра, по которому нужно найти объект
     * @return Возвращает объект, найденный по имени режиссёра. Если объект не найден, вернёт null
     */
    public Movie findElementByDirector(String directorName) {
        for (Movie m : MoviesCollection) {
            if (m.getDirector().getName().equals(directorName)) {
                return m;
            }
        }
        return null;
    }

    /**
     * находит и удаляет один элемент коллекции, directorName которого равен заданному
     *
     * @param directorName
     */
    public void removeElementByDirectorName(String directorName) {
        removeElement(findElementByDirector(directorName));
        sortCollection();
    }

    /**
     * Метод удаляет все элементы из коллекции, чей hashcode меньше заданного.
     *
     * @param movie заданный объект, с хэшкодом которого сравниваются все объекты коллекции
     */
    public void removeAllLower(Movie movie) {
        System.out.println("Hashcode введённого объекта: " + movie.hashCode() + ". ");
        PriorityQueue<Movie> movieForDelete = new PriorityQueue<>(idComparator);
        for (Movie m : MoviesCollection) {
            if (m.hashCode() < movie.hashCode()) {
                System.out.println("Удаляю объект с hashcode " + m.hashCode());
                movieForDelete.add(m);
            }
        }
        for (Movie m : movieForDelete) {
            removeElement(m);
        }
        sortCollection();
    }

    /**
     * Метод удаляет все элементы из коллекции, чей hashcode больше заданного.
     *
     * @param movie заданный movie с хэшкодом которого сравниваются объекты.
     */
    public void removeAllGreater(Movie movie) {
        System.out.println("Hashcode введённого объекта: " + movie.hashCode() + ". ");
        PriorityQueue<Movie> movieForDelete = new PriorityQueue<>();
        for (Movie m : MoviesCollection) {
            if (m.hashCode() > movie.hashCode()) {
                System.out.println("Удаляю объект с hashcode " + m.hashCode());
                movieForDelete.add(m);
            }
        }
        for (Movie m : movieForDelete) {
            removeElement(m);
        }
        sortCollection();
    }

    /**
     * Метод выполняет сортировку по возрастанию idList и
     *
     * @return Возвращает 0й элемент (минимальный)
     */
    public long getMinElement() {
        int minEl = 999999999;
        for (Movie m : MoviesCollection) {
            if (m.hashCode() < minEl) {
                minEl = m.hashCode();
            }
        }
        return minEl;
    }


    /**
     * Метод выполняет проверку, меньше ли заданный id минимального из имеющихся id элементов в коллекции. Если да, то добавляет заданный объект в коллекцию.
     *
     * @param movie объект, который нужно добавить.
     */
    public void addElementIfLowerMin(Movie movie) {
        System.out.println("Hashcode введённого объекта: " + movie.hashCode() + ". ");
        if (movie.hashCode() < getMinElement()) {
            addElement(movie);
        } else {
            System.out.println("Элемент не был добавлен.");
        }
    }

    /**
     * Метод выводит информацию о коллекции: тип, дата инициализации, количество элементов и выполняет перебор всех элементов с выводом их номера ID
     */
    public void printInfo() {
        System.out.println("Тип коллекции: " + type + ". \n" + "Дата инициализации: " + initTime + ". \n" + "Количество элементов: "
                + MoviesCollection.size() + ". \n" + "Элементы коллекции по хэшкодам: ");
        for (Movie m : MoviesCollection) {
            System.out.print(m.hashCode() + " ");
        }
        System.out.println("\n");
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным ID в коллекции
     *
     * @param id заданный ID
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingID(long id) {
        for (Movie m : MoviesCollection) {
            Long idObject = id;
            if (idObject.equals(m.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным directorName в коллекции
     *
     * @param directorName заданный directorName
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingDirectorName(String directorName) {
        for (Movie m : MoviesCollection) {
            if (directorName.equals(m.getDirector().getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным oscarsCount в коллекции
     *
     * @param oscarsCount заданный номер oscarsCount
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingOscarsCount(long oscarsCount) {
        for (Movie m : MoviesCollection) {
            Long oscarsCountObject = oscarsCount;
            if (oscarsCountObject.equals(m.getOscarsCount())) {
                return true;
            }
        }
        return false;
    }

    public void sortCollection() {
        PriorityQueue<Movie> sortedCollection = new PriorityQueue<>(idComparator);
        for (long id : idList) {
            sortedCollection.add(findElementByID(id));
        }
        MoviesCollection = sortedCollection;
    }

    public static Comparator<Movie> idComparator = new Comparator<Movie>() {

        @Override
        public int compare(Movie c1, Movie c2) {
            if (c1.getId() > c2.getId()) {
                return 1;
            } else if (c1.getId() < c2.getId()) {
                return -1;
            } else {
                return 0;
            }
        }
    };
}


//            } else if (c1.getId() < c2.getId("Maks, you are sunshine!!!!!!!!!")) {