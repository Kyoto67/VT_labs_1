package commands;

import util.CollectionManager;

import java.io.IOException;

public class Show extends AbstractCommand{

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public Show(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    /**
     * Метод вызывает у Менеджера коллекции вывод в консоль информации о коллекции.
     * @param argument не требует аргументов
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#printCollection()
     */
    @Override
    public boolean exec(String argument) throws IOException {
        manager.printCollection();
        return true;
    }
}
