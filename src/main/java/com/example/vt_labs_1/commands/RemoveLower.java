package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;
import com.example.vt_labs_1.utility.User;

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
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.removeAllLower(argument, user));
        return true;
    }
}
