package commands;

import data.Movie;
import util.Asker;
import util.CollectionManager;

import java.io.IOException;

public class RemoveLower extends AbstractCommand{

    private final CollectionManager manager;

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

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Вызывает удаление всех элементов коллекции, чей id меньше переданного в качестве аргумента программе.
     * @param argument номер id, элементы с меньшим которого должны быть удалены.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see Asker#askIDForExec() 
     * @see CollectionManager#removeAllLower(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        Movie movieForCompare = Asker.askMovie();
        manager.removeAllLower(movieForCompare);
        return true;
    }
}
