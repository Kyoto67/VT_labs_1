package util;

import data.Movie;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * класс, объект которого хранит в себе коллекцию и управляет ей.
 */
public class CollectionManager {

    private PriorityQueue<Movie> MoviesCollection = new PriorityQueue<>();
    private final String type;
    private final LocalDateTime initTime;
    private ArrayList<Long> idList = new ArrayList<>();

    /**
     * Конструктор класса. Автоматически создаёт коллекцию, заполненную рандомными значениями, сохраняет её в файл и загружает из файла (потому что так сказано в задании).
     * Записывает idList, в котором в реальном времени отображается наличие элементов по их IDшникам.
     * Записывает время инициализации коллекции и её тип.
     * @throws IOException
     */
    public CollectionManager() throws IOException {
        createCollection();
        loadCollection("random.json");
        for (long i=1; i<20; i++){
            idList.add(i);
        }
        type="PriorityQueue";
        initTime=LocalDateTime.now();
    }

    /**
     * Метод обращается к Файл-Менеджеру и генерирует коллекцию со случайными данными. Записывает её в файл random.json
     * @throws IOException
     * @see FileManager#createRandomCollection()
     */
    public void createCollection() throws IOException {
        FileManager.createRandomCollection();
    }

    /**
     * Метод обращается к Файл-Менеджеру, тот читает коллекцию и передаёт её методу, метод записывает полученную коллекцию в своё поле.
     * @param filename имя файла, содержащего коллекцию, которую необходимо загрузить.
     * @throws IOException
     * @see FileManager#readCollection(String)
     */
    public void loadCollection(String filename) throws IOException {
        MoviesCollection=FileManager.readCollection(filename);
    }

    /**
     * Метод, используемый для тесто, оставлен для возможных тестов в будущем.
     * @param moviesCollection
     */
    public void loadTestCollection(PriorityQueue<Movie> moviesCollection) {
        this.MoviesCollection = moviesCollection;
    }

    /**
     * Метод выполняет замену элемента с указанным id на новый (с тем же id)
     * @param newMovie объект Movie, который необходимо добавить в коллекцию на замену старому.
     * @param id id элемента, который нужно заменить
     */
    public void replaceElementByID(Movie newMovie, long id) {
        removeElement(findElementByID(id));
        MoviesCollection.add(newMovie);
    }

    /**
     * Метод добавляет объект в коллекцию, предварительно устанавливая ему id минимальный из тех, что не будет повторён.
     * @param movie полуает на вход объект типа Movie, который необходимо добавить
     */
    public void addElement(Movie movie){
        movie.setId(addID());
        MoviesCollection.add(movie);
    }

    /**
     * Метод удаляет объект из коллекции и удаляет из idList ID удалённого элемента.
     * @param movie объект, который нужно удалить из коллекции.
     */
    public void removeElement(Movie movie){
        removeID(movie);
        MoviesCollection.remove(movie);
    }

    /**
     * Метод удаляет элемент из коллекции по указанному ID
     * @param id ID объекта, который нужно удалить
     */
    public void removeElementByID(long id){
        removeElement(findElementByID(id));
    }

    /**
     * Метод перебирает элементы коллекции и ищет соответствие по ID
     * @param id ID элемента, который нужно найти
     * @return Возвращает объект типа Movie, которому соответствует искомый ID. Если элемент не найден, вернёт null
     */
    public Movie findElementByID(long id){
        for (Movie m : MoviesCollection){
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
    public PriorityQueue<Movie> getMoviesCollection(){
        return MoviesCollection;
    }

    /**
     * Метод удаляет все элементы в коллекции и все элементы в idList
     */
    public void removeAllElements(){
        idList.removeAll(idList);
        MoviesCollection.removeAll(MoviesCollection);
    }

    /**
     * Метод генерирует имя файла для сохранения, передаёт его и имеющуюся коллекцию Файл-Менеджеру
     * @throws IOException
     * @see FileManager#saveCollection(PriorityQueue, String)
     */
    public void saveCollection() throws IOException {
        FileManager.saveCollection(MoviesCollection,new Date()+" saved.json");
    }

    /**
     * Метод выводит по-очерёдно строковое представление объектов коллекции.
     */
    public void printCollection(){
        for (Movie m : MoviesCollection){
            System.out.println(m.toString());
        }
    }

    /**
     * Метод находит один элемент в коллекции по числу oscarsCount
     * @param count число, в соответствие которому нужно найти объект коллекции
     * @return Возвращает найденный объект типа Movie, если объект по заданному номеру не найден, вернёт null
     */
    public Movie findElementByOscarsCount(long count){
        for (Movie m : MoviesCollection){
            Long OscarsCount=m.getOscarsCount();
            if (OscarsCount.equals(count)){
                return m;
            }
        }
        return null;
    }

    /**
     * Запускает цикл, пока может найти элемент по заданному oscarsCount, он его удаляет из коллекции.
     * @param count число oscarsCount, по которому нужно удалить объект
     */
    public void removeByOscarsCount(long count){
        while (!(findElementByOscarsCount(count)==null)) {
            removeElement(findElementByOscarsCount(count));
        }
    }

    /**
     * перебирает idList, сравнивает с ID переданного объекта. Если находит соответствие, то удаляет id из idList
     * @param m объект, id которого нужно удалить
     */
    public void removeID(Movie m){
        for (Long id : idList){
            if (id.equals(m.getId())){
                idList.remove(id);
                break;
            }
        }
    }

    /**
     * Метод ищет минимальное положительное значение id, которое не встречается в idList, добавляет его
     * @return и возвращает добавленное значение
     */
    public long addID(){
        long id = 1;
        while (id>0){
            if (!(checkMatchingID(id))){
                idList.add(id);
                break;
            } else{
                id+=1;
            }
        }
        return id;
    }

    /**
     * Метод считывает все значения oscarsCount из объектов коллекции и записывает их в longList, сортирует и выводит в порядке убывания.
     */
    public void printAllDescendingOscarsCount(){
        ArrayList<Long> longList = new ArrayList<>();
        for (Movie m : MoviesCollection){
            longList.add(m.getOscarsCount());
        }
        Collections.sort(longList);
        Collections.reverse(longList);
        for (long l : longList){
            System.out.println(l);
        }
    }

    /**
     * Перебирает элементы коллекции, находит один элемент по заданному имени режиссёра
     * @param directorName имя режиссёра, по которому нужно найти объект
     * @return Возвращает объект, найденный по имени режиссёра. Если объект не найден, вернёт null
     */
    public Movie findElementByDirector(String directorName){
        for (Movie m : MoviesCollection){
            if(m.getDirector().getName().equals(directorName)){
                return m;
            }
        }
        return null;
    }

    /**
     * находит и удаляет один элемент коллекции, directorName которого равен заданному
     * @param directorName
     */
    public void removeElementByDirectorName(String directorName){
        removeElement(findElementByDirector(directorName));
    }

    /**
     * Метод удаляет все элементы из коллекции, чей ID меньше заданного.
     * @param id заданный ID
     */
    public void removeAllLowerByID(long id){
        for (long i=1; i<id; i++){
            removeElement(findElementByID(i));
        }
    }

    /**
     * Метод удаляет все элементы из коллекции, чей ID больше заданного.
     * @param id заданный ID
     */
    public void removeAllGreaterByID(long id) {
        for (long i = (long) (MoviesCollection.size()); i > id ; i--){
            removeElement(findElementByID(i));
        }
    }

    /**
     * Метод выполняет сортировку по возрастанию idList и
     * @return Возвращает 0й элемент (минимальный)
     */
    public long getMinElementID(){
        Collections.sort(idList);
        return idList.get(0);
    }

    /**
     * Метод выполняет проверку, меньше ли заданный id минимального из имеющихся id элементов в коллекции. Если да, то добавляет заданный объект в коллекцию.
     * @param movie объект, который нужно добавить.
     * @param id id, по которому выполняется проверка на "меньше минимального"
     */
    public void addElementIfIDLowerMin(Movie movie, long id){
        if (id<getMinElementID()){
            movie.setId(id);
            idList.add(id);
            MoviesCollection.add(movie);
        }
    }

    /**
     * Метод выводит информацию о коллекции: тип, дата инициализации, количество элементов и выполняет перебор всех элементов с выводом их номера ID
     */
    public void printInfo(){
        System.out.println("Тип коллекции: "+type+". \n"+"Дата инициализации: "+initTime+". \n"+"Количество элементов: "
                +MoviesCollection.size()+". \n"+"Элементы коллекции по идентификаторам: ");
        Collections.sort(idList);
        for (long id : idList){
            System.out.print(id+" ");
        }
        System.out.println("\n");
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным ID в коллекции
     * @param id заданный ID
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingID(long id){
        for (Movie m : MoviesCollection){
            Long idObject = id;
            if (idObject.equals(m.getId())){
                return true;
            }
        }
        return false;
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным directorName в коллекции
     * @param directorName заданный directorName
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingDirectorName(String directorName){
        for (Movie m : MoviesCollection){
            if (directorName.equals(m.getDirector().getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным oscarsCount в коллекции
     * @param oscarsCount заданный номер oscarsCount
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingOscarsCount(long oscarsCount){
        for (Movie m : MoviesCollection){
            Long oscarsCountObject = oscarsCount;
            if (oscarsCountObject.equals(m.getOscarsCount())){
                return true;
            }
        }
        return false;
    }
}
