package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.utility.*;
import com.example.vt_labs_1.utility.Module;

public class UpdateByID extends AbstractCommand {

    private CollectionManager collectionManager;
    private Movie argument;
    private User user;

    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public UpdateByID(String name, String description) {
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
     * Метод конвертирует аргумент в номер id элемента, который нужно обновить. Если номер введён неверно или в коллекции нет элемента с таким id, запрашивает повторный ввод.
     * Запрашивает у пользователя данные обновлённого объекта, если он хочет ввести их вручную, или заполняет автоматически случайными значениями.
     * Вызывает у пользователя замену элемента по номеру ID.
     *
     * @return Возвращает True при выполнении.
     * @see CollectionManager#checkMatchingID(long, User)
     * @see CollectionManager#replaceElementByID(Movie, long, User)
     */

    @Override
    public boolean exec() {
        long id = argument.getId();
        if (collectionManager.checkMatchingID(id,user)) {
            collectionManager.replaceElementByID(argument, id, user);
            Module.addMessage("Объёкт с идентификатором " + id + " обновлён.");
            return true;
        } else {
            Module.addMessage("В зоне действия ваших прав отсутствует объект для замены.");
            return false;
        }
    }

}