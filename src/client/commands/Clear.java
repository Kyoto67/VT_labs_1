package client.commands;

import server.util.CollectionManager;

public class Clear extends  AbstractCommand{

    private CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public Clear(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    public Clear(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод удаляет все элементы коллекции.
     * @param argument не требует аргументов, передаётся пустая строка.
     * @return Возвращает True при выполнении.
     * @see CollectionManager#removeAllElements()
     */
    @Override
    public boolean exec(String argument) {
        manager.removeAllElements();
        return true;
    }
}
