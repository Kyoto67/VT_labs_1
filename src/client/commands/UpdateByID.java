package client.commands;

import client.data.Movie;
import client.util.Asker;
import server.util.CollectionManager;

public class UpdateByID extends AbstractCommand {
    private CollectionManager manager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param manager     сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public UpdateByID(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager = manager;
    }

    public UpdateByID(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод конвертирует аргумент в номер id элемента, который нужно обновить. Если номер введён неверно или в коллекции нет элемента с таким id, запрашивает повторный ввод.
     * Запрашивает у пользователя данные обновлённого объекта, если он хочет ввести их вручную, или заполняет автоматически случайными значениями.
     * Вызывает у пользователя замену элемента по номеру ID.
     *
     * @return Возвращает True при выполнении.
     * @see CollectionManager#checkMatchingID(long)
     * @see Asker
     * @see CollectionManager#replaceElementByID(Movie, long)
     */

    @Override
    public boolean exec(String argument) {
        return false;
    }

    public boolean exec(Movie movie) {
        long id = movie.getId();
        if(manager.checkMatchingID(id)){
            manager.replaceElementByID(movie,id);
            return true;
        } else {
            System.out.println("Отсутствует объект для замены.");
            return false;
        }
    }

}
