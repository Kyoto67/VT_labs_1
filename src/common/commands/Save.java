package common.commands;

import server.Server;
import server.util.CollectionManager;

import java.io.IOException;

public class Save extends AbstractCommand{

    private CollectionManager manager;
    private static final long serialVersionUID = 16L;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public Save(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    public Save(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * метод вызывает у Менеджера коллекции сохранение коллекции в файл. Имя файла генерируется автоматически.
     * @param argument не требует аргументов, вызывается с пустой строкой.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#saveCollection()
     */
    @Override
    public boolean exec(String argument) throws IOException {
        Server.outputMessage = manager.saveCollection();
        return true;
    }
}
