package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;

public class Show extends AbstractCommand{

    private CollectionManager collectionManager;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public Show(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод вызывает у Менеджера коллекции вывод в консоль информации о коллекции.
     * @return Возвращает True при выполнении
     * @see CollectionManager#printCollection()
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.printCollection());
        return true;
    }
}
