package commands;

import utility.Module;
import utility.CollectionManager;
import utility.User;

import java.io.IOException;

public class RemoveAnyByDirector extends AbstractCommand {

    private CollectionManager manager;
    private String argument;
    private User user;


    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public RemoveAnyByDirector(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.manager = collectionManager;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
    public void setUser(User user){
        this.user=user;
    }

    /**
     * Метод вызывает удаление из коллекции элемент, имя режиссёра которого равно переданному аргументу. Если соответствующий объект не был найден, запрашивает повторный ввод.
     *
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#checkMatchingDirectorName(String)
     * @see CollectionManager#removeElementByDirectorName(String)
     */
    @Override
    public boolean exec() throws IOException {
        String directorName = argument;
        if (!manager.checkMatchingDirectorName(directorName)) {
            Module.addMessage("Режиссёра с таким именем не найдено.");
            return false;
        } else {
            if(!(manager.checkMatchingOwnerName(user))){
                Module.addMessage("Пользователь не является хозяином объекта.");
                return false;
            }
            Module.addMessage(manager.removeElementByDirectorName(directorName));
            return true;
        }
    }
}
