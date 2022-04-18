package common.commands;

import server.Server;
import server.util.CollectionManager;

import java.io.IOException;

public class Info extends AbstractCommand{

    private CollectionManager manager;
    private static final long serialVersionUID = 9L;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public Info(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    public Info(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод вызывает вывод информации о коллекции у Менеджера коллекций.
     * @param argument не требует аргументов, вызывается с пустой строкой.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#printInfo()
     */
    @Override
    public boolean exec(String argument) throws IOException {
        Server.outputMessage=manager.printInfo();
        return true;
    }
}
