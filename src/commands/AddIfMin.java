package commands;

import data.Coordinates;
import data.Location;
import data.Movie;
import data.Person;
import util.Asker;
import util.CollectionManager;
import util.GeneratingRandomInfo;

import java.io.IOException;
import java.util.Date;

public class AddIfMin extends AbstractCommand{

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public AddIfMin(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    /**
     * Метод считывает id, передаваемый в качестве аргумента. Если id введён неверно, запрашивает повторное введение, пока не сможет распознать данные.
     * После создаёт объект типа Movie, заполняет его случайными данными или данными, введёнными пользователем (как решит пользователь),
     * Добавляет объект в коллекцию, если переданный id в качестве аргумента меньше минимального имеющегося в коллекции.
     * @param argument id нового объекта Movie
     * @return Возвращает True при выполнении программы
     * @throws IOException
     * @see Asker
     * @see CollectionManager#addElementIfLowerMin(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        Movie newMovie = Asker.askMovie();
        manager.addElementIfLowerMin(newMovie);
        return true;
    }
}
