package com.example.vt_labs_1.commands;


import com.example.vt_labs_1.data.Movie;
import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;
import com.example.vt_labs_1.utility.User;

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