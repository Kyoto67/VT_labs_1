package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;

public class PrintFieldDescendingOscarsCount extends AbstractCommand{

    private CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public PrintFieldDescendingOscarsCount(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод вызывает вывод всех значений oscarsCount у объектов коллекции в порядке убывания.
     * @return Возвращает True при выполнении
     * @see CollectionManager#printAllDescendingOscarsCount()
     */
    @Override
    public boolean exec() {
        Module.addMessage(manager.printAllDescendingOscarsCount());
        return true;
    }
}
