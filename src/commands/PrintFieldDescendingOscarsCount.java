package commands;

import utility.Module;
import utility.CollectionManager;

import java.io.IOException;

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
     * @throws IOException
     * @see CollectionManager#printAllDescendingOscarsCount()
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage(manager.printAllDescendingOscarsCount());
        return true;
    }
}
