package common.commands;

import server.util.CollectionManager;

import java.io.IOException;

public class RemoveAllByOscarsCount extends AbstractCommand{

    private CollectionManager manager;
    private static final long serialVersionUID = 11L;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public RemoveAllByOscarsCount(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    public RemoveAllByOscarsCount(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод пытается считать из аргумента число oscarsCount, если данные введены неверно, запрашивает повторный ввод.
     * После вызывает у менеджера коллекций удаление всех элементов, чей oscarsCount равен переданному команде в аргументе.
     * @param argument число oscarsCount, объекты обладающие которым должны быть удалены из коллекции.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#removeByOscarsCount(long) 
     */
    @Override
    public boolean exec(String argument) throws IOException {
        manager.removeByOscarsCount(Long.parseLong(argument));
        return true;
    }
}
