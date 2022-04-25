package commands;

import utility.Module;
import utility.CollectionManager;

public class RemoveByID extends AbstractCommand{

    private CollectionManager manager;
    private Long argument;


    /**
     * конструктор
     * @param name
     * @param description
     */
    public RemoveByID(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    public void setArgument(Long argument) {
        this.argument = argument;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Если элемент с введённым ID отсутствует в коллекции, запрашивает повторный ввод.
     * Вызывает удаление элемента из коллекции по номеру id
     * @return Возвращает True при выполнении
     * @see CollectionManager#checkMatchingID(long)
     * @see CollectionManager#removeElementByID(long)
     */
    @Override
    public boolean exec() {
        long id = argument;
        if (manager.checkMatchingID(id)) {
            manager.removeElementByID(id);
            Module.addMessage("Объект с идентификатором "+id+" удалён.");
            return true;
        } else {
            Module.addMessage("Объекта с таким ID нет в коллекции.");
            return false;
        }
    }
}
