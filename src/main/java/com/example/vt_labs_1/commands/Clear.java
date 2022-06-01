package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.CollectionManager;
import com.example.vt_labs_1.utility.User;

public class Clear extends  AbstractCommand{

    private CollectionManager manager;
    private User user;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public Clear(String name, String description){
        super(name, description);
        this.manager=null;
    }

    public void setUser(User user){
        this.user=user;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод удаляет все элементы коллекции.
     * @return Возвращает True при выполнении.
     * @see CollectionManager#removeAllElements(User)
     */
    @Override
    public boolean exec() {
        manager.removeAllElements(user);
        Module.addMessage("Коллекция очищена");
        return true;
    }
}
