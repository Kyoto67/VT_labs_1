package client.commands;

import server.util.CollectionManager;

import java.io.IOException;

public class Save extends AbstractCommand{

    private CollectionManager manager;

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
        manager.saveCollection();
        return true;
    }
}
