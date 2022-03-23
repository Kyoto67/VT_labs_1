package commands;

import data.Movie;
import util.Asker;
import util.CollectionManager;

import java.io.IOException;

public class RemoveGreater extends AbstractCommand{

    private final CollectionManager manager;

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

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Вызывает удаление всех элементов коллекции, чей id больше переданного в качестве аргумента программе.
     * @param argument номер id, элементы с большим которого должны быть удалены.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see Asker#askIDForExec() 
     * @see CollectionManager#removeAllGreater(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        Movie movieForCompare = Asker.askMovie();
        manager.removeAllGreater(movieForCompare);
        return true;
    }
}
