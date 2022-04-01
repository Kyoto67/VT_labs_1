package client.commands;

import server.util.CollectionManager;

import java.io.IOException;

public class PrintFieldDescendingOscarsCount extends AbstractCommand{

    private CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public PrintFieldDescendingOscarsCount(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    public PrintFieldDescendingOscarsCount(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод вызывает вывод всех значений oscarsCount у объектов коллекции в порядке убывания.
     * @param argument не требует аргументов
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#printAllDescendingOscarsCount()
     */
    @Override
    public boolean exec(String argument) throws IOException {
        manager.printAllDescendingOscarsCount();
        return true;
    }
}
