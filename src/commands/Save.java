package commands;

import utility.Module;
import utility.CollectionManager;

import java.io.IOException;

public class Save extends AbstractCommand{

    private CollectionManager collectionManager;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public Save(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * метод вызывает у Менеджера коллекции сохранение коллекции в файл. Имя файла генерируется автоматически.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#saveCollection()
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage(collectionManager.saveCollection());
        return true;
    }
}
