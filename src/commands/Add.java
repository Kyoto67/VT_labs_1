package commands;

import data.Coordinates;
import data.Location;
import data.Movie;
import data.Person;
import util.Asker;
import util.CollectionManager;
import util.GeneratingRandomInfo;

import java.util.Date;

/**
 * Команда добавления объекта в коллекцию.
 */
public class Add extends AbstractCommand {

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public Add(String name, String description, CollectionManager manager){
        super(name,description);
        this.manager=manager;
    }

    /**
     * Метод создаёт объект типа Movie, спрашивает у пользователя, хочет ли он ввести данные самостоятельно или заполнить случайными значениями.
     * Запрашивает данные, если пользователь решает ввести самостоятельно
     * @param argument не требует аргументов, передаётся пустая строка.
     * @return Возвращает True при выполнении.
     * @see Asker
     */
    @Override
    public boolean exec(String argument) {
        Movie newMovie = Asker.askMovie();
        manager.addElement(newMovie);
        return true;
    }
}
