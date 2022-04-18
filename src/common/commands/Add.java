package common.commands;

import common.data.Movie;
import server.util.CollectionManager;

/**
 * Команда добавления объекта в коллекцию.
 */
public class Add extends AbstractCommand {

    private CollectionManager manager;
    private static final long serialVersionUID = 2L;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param manager     сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public Add(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager = manager;
    }

    public Add(String name, String description){
        super(name, description);
        manager=null;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод создаёт объект типа Movie, спрашивает у пользователя, хочет ли он ввести данные самостоятельно или заполнить случайными значениями.
     * Запрашивает данные, если пользователь решает ввести самостоятельно
     *
     * @param argument не требует аргументов, передаётся пустая строка.
     * @return Возвращает True при выполнении.
     */
    @Override
    public boolean exec(String argument) {
        return false;
    }

    public boolean exec(Movie movie){
        manager.addElement(movie);
        return true;
    }
}
