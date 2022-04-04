package client.commands;

import client.data.Movie;
import client.util.Asker;
import server.util.CollectionManager;

import java.io.IOException;

public class AddIfMin extends AbstractCommand{

    private CollectionManager manager;
    private static final long serialVersionUID = 3L;

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

    public AddIfMin(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
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
        return false;
    }

    public boolean exec(Movie movie){
        manager.addElementIfLowerMin(movie);
        return true;
    }
}
