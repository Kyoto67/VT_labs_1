package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;
import com.example.vt_labs_1.utility.User;

public class RemoveAllByOscarsCount extends AbstractCommand{

    private CollectionManager manager;
    private Long argument;
    private User user;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public RemoveAllByOscarsCount(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    public void setArgument(Long argument) {
        this.argument = argument;
    }
    public void setUser(User user){
        this.user=user;
    }

    /**
     * Метод пытается считать из аргумента число oscarsCount, если данные введены неверно, запрашивает повторный ввод.
     * После вызывает у менеджера коллекций удаление всех элементов, чей oscarsCount равен переданному команде в аргументе.
     * @return Возвращает True при выполнении
     * @see CollectionManager#removeByOscarsCount(long, User)
     */
    @Override
    public boolean exec()  {
        Module.addMessage(manager.removeByOscarsCount(argument, user));
        return true;
    }
}
