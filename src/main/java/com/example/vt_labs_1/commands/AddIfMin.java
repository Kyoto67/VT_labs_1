package com.example.vt_labs_1.commands;


import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;
import com.example.vt_labs_1.utility.User;

import java.io.IOException;


public class AddIfMin extends AbstractCommand {

    private CollectionManager collectionManager;
    private Movie argument;

    private User user;


    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public AddIfMin(String name, String description) {
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
     * Метод считывает id, передаваемый в качестве аргумента. Если id введён неверно, запрашивает повторное введение, пока не сможет распознать данные.
     * После создаёт объект типа Movie, заполняет его случайными данными или данными, введёнными пользователем (как решит пользователь),
     * Добавляет объект в коллекцию, если переданный id в качестве аргумента меньше минимального имеющегося в коллекции.
     *
     * @return Возвращает True при выполнении программы
     */
    @Override
    public boolean exec() {
            Module.addMessage(collectionManager.addElementIfLowerMin(argument, user));
            return true;
    }
}