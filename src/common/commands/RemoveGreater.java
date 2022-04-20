package common.commands;

import common.data.Movie;
import server.Server;
import server.util.CollectionManager;

import java.io.IOException;

public class RemoveGreater extends AbstractCommand{

    private CollectionManager manager;
    private static final long serialVersionUID = 14L;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public RemoveGreater(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    public RemoveGreater(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Вызывает удаление всех элементов коллекции, чей id больше переданного в качестве аргумента программе.
     * @param argument номер id, элементы с большим которого должны быть удалены.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#removeAllGreater(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        return false;
    }

    public boolean exec(Movie movie){
        Server.outputMessage = manager.removeAllGreater(movie);
        return true;
    }
}
