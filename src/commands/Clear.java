package commands;

import utility.Module;
import utility.CollectionManager;

public class Clear extends  AbstractCommand{

    private CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public Clear(String name, String description){
        super(name, description);
        this.manager=null;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод удаляет все элементы коллекции.
     * @return Возвращает True при выполнении.
     * @see CollectionManager#removeAllElements()
     */
    @Override
    public boolean exec() {
        manager.removeAllElements();
        Module.addMessage("Коллекция очищена");
        return true;
    }
}
