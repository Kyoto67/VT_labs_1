package commands;

import utility.Module;
import utility.CollectionManager;
import java.io.IOException;

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
     * @throws IOException
     * @see CollectionManager#printInfo()
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage(manager.printInfo());
        return true;
    }
}
