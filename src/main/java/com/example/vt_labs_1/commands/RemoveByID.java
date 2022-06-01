package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;
import com.example.vt_labs_1.utility.User;

public class RemoveByID extends AbstractCommand {

    private CollectionManager manager;
    private Long argument;
    private User user;


    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public RemoveByID(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    public void setArgument(Long argument) {
        this.argument = argument;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Если элемент с введённым ID отсутствует в коллекции, запрашивает повторный ввод.
     * Вызывает удаление элемента из коллекции по номеру id
     *
     * @return Возвращает True при выполнении
     * @see CollectionManager#checkMatchingID(long, User)
     * @see CollectionManager#removeElementByID(long)
     */
    @Override
    public boolean exec() {
        long id = argument;
        if (manager.checkMatchingID(id, user)) {
                manager.removeElementByID(id);
                Module.addMessage("Объект с идентификатором " + id + " удалён.");
                return true;
        } else {
            Module.addMessage("Объекта, принадлежащего вам, с таким ID нет в коллекции.");
            return false;
        }
    }
}
