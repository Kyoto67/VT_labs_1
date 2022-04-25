package commands;

import utility.Module;
import utility.CollectionManager;
import java.io.IOException;

public class RemoveAllByOscarsCount extends AbstractCommand{

    private CollectionManager manager;
    private Long argument;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public RemoveAllByOscarsCount(String name, String description){
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    public void setArgument(Long argument) {
        this.argument = argument;
    }

    /**
     * Метод пытается считать из аргумента число oscarsCount, если данные введены неверно, запрашивает повторный ввод.
     * После вызывает у менеджера коллекций удаление всех элементов, чей oscarsCount равен переданному команде в аргументе.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#removeByOscarsCount(long) 
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage(manager.removeByOscarsCount(argument));
        return true;
    }
}
