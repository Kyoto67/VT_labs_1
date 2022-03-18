package commands;

import util.Asker;
import util.CollectionManager;

import java.io.IOException;

public class RemoveAnyByDirector extends AbstractCommand{

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public RemoveAnyByDirector(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager = manager;
    }

    /**
     * Метод вызывает удаление из коллекции элемент, имя режиссёра которого равно переданному аргументу. Если соответствующий объект не был найден, запрашивает повторный ввод.
     * @param argument имя объекта Person, находящегося в поле объекта Movie, который нужно удалить.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#checkMatchingDirectorName(String)
     * @see CollectionManager#removeElementByDirectorName(String) 
     */
    @Override
    public boolean exec(String argument) throws IOException {
        String directorName = argument;
        if (!manager.checkMatchingDirectorName(directorName)) {
            System.out.println("Режиссёра с таким именем не найдено.");
            return false;
            } else {
            manager.removeElementByDirectorName(directorName);
            return true;
        }
    }
}
