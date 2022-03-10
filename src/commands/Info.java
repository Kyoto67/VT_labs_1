package commands;

import util.CollectionManager;

import java.io.IOException;

public class Info extends AbstractCommand{

    private final CollectionManager manager;

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

    /**
     * Метод вызывает вывод информации о коллекции у Менеджера коллекций.
     * @param argument не требует аргументов, вызывается с пустой строкой.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#printInfo()
     */
    @Override
    public boolean exec(String argument) throws IOException {
        manager.printInfo();
        return true;
    }
}
