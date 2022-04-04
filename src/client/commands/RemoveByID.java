package client.commands;

import client.util.Asker;
import server.util.CollectionManager;

public class RemoveByID extends AbstractCommand{

    private CollectionManager manager;
    private static final long serialVersionUID = 13L;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public RemoveByID(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager = manager;
    }

    public RemoveByID(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Если элемент с введённым ID отсутствует в коллекции, запрашивает повторный ввод.
     * Вызывает удаление элемента из коллекции по номеру id
     * @param argument номер id элемента, который нужно удалить
     * @return Возвращает True при выполнении
     * @see CollectionManager#checkMatchingID(long)
     * @see CollectionManager#removeElementByID(long)
     * @see Asker#askIDForExec()
     */
    @Override
    public boolean exec(String argument) {
        long id = Long.valueOf(argument);
        if (manager.checkMatchingID(id)) {
            manager.removeElementByID(id);
            return true;
        } else {
            System.out.println("Объекта с таким ID нет в коллекции.");
            return false;
        }
    }
}
