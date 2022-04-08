package common.commands;

import common.data.Movie;
import server.util.CollectionManager;

import java.io.IOException;

public class RemoveLower extends AbstractCommand{

    private CollectionManager manager;
    private static final long serialVersionUID = 15L;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public RemoveLower(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    public RemoveLower(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Вызывает удаление всех элементов коллекции, чей id меньше переданного в качестве аргумента программе.
     * @param argument номер id, элементы с меньшим которого должны быть удалены.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#removeAllLower(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        return false;
    }

    public boolean exec(Movie movie){
        manager.removeAllLower(movie);
        return true;
    }
}
