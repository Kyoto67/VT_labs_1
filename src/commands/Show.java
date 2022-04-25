package commands;

import utility.Module;
import utility.CollectionManager;

import java.io.IOException;

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
     * @throws IOException
     * @see CollectionManager#printCollection()
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage(collectionManager.printCollection());
        return true;
    }
}
