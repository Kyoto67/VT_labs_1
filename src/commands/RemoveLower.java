package commands;

import data.*;
import utility.Module;
import utility.CollectionManager;
import utility.User;

import java.io.IOException;

public class RemoveLower extends AbstractCommand {

    private CollectionManager collectionManager;
    private Movie argument;
    private User user;


    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public RemoveLower(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setArgument(Movie argument) {
        this.argument = argument;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Вызывает удаление всех элементов коллекции, чей id меньше переданного в качестве аргумента программе.
     *
     * @return Возвращает True при выполнении
     * @throws IOException
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage(collectionManager.removeAllLower(argument, user));
        return true;
    }
}
