package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.CollectionManager;
import com.example.vt_labs_1.utility.Module;

public class getTable extends AbstractCommand {

    private CollectionManager collectionManager;

    /**
     * конструктор
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public getTable(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.getTable());
        return false;
    }
}
