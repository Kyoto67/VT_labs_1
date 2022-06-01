package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;

public class Info extends AbstractCommand{

    private CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public Info(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод вызывает вывод информации о коллекции у Менеджера коллекций.
     * @return Возвращает True при выполнении
     * @see CollectionManager#printInfo()
     */
    @Override
    public boolean exec()  {
        Module.addMessage(manager.printInfo());
        return true;
    }
}
