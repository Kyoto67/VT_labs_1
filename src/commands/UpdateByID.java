package commands;

import data.*;
import utility.Module;
import utility.CollectionManager;

public class UpdateByID extends AbstractCommand {

    private CollectionManager collectionManager;
    private Movie argument;

    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public UpdateByID(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setArgument(Movie argument) {
        this.argument = argument;
    }

    /**
     * Метод конвертирует аргумент в номер id элемента, который нужно обновить. Если номер введён неверно или в коллекции нет элемента с таким id, запрашивает повторный ввод.
     * Запрашивает у пользователя данные обновлённого объекта, если он хочет ввести их вручную, или заполняет автоматически случайными значениями.
     * Вызывает у пользователя замену элемента по номеру ID.
     *
     * @return Возвращает True при выполнении.
     * @see CollectionManager#checkMatchingID(long)
     * @see CollectionManager#replaceElementByID(Movie, long)
     */

    @Override
    public boolean exec() {
        long id = argument.getId();
        if (collectionManager.checkMatchingID(id)) {
            collectionManager.replaceElementByID(argument, id);
            Module.addMessage("Объёкт с идентификатором " + id + " обновлён.");
            return true;
        } else {
            Module.addMessage("Отсутствует объект для замены.");
            return false;
        }
    }

}