package commands;


import data.*;
import utility.Module;
import utility.CollectionManager;
import utility.User;

/**
 * Команда добавления объекта в коллекцию.
 */
public class Add extends AbstractCommand {

    private CollectionManager collectionManager;
    private Movie argument;
    private User user;

    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public Add(String name, String description) {
        super(name, description);
        collectionManager = null;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setArgument(Movie argument) {
        this.argument = argument;
    }

    public void setUser(User user){
        this.user=user;
    }

    /**
     * Метод создаёт объект типа Movie, спрашивает у пользователя, хочет ли он ввести данные самостоятельно или заполнить случайными значениями.
     * Запрашивает данные, если пользователь решает ввести самостоятельно
     *
     * @return Возвращает True при выполнении.
     */
    @Override
    public boolean exec() {
            Module.addMessage(collectionManager.addElement(argument,user));
            return true;
    }
}