package commands;


import data.*;
import utility.Module;
import utility.CollectionManager;

import java.io.IOException;

public class AddIfMin extends AbstractCommand {

    private CollectionManager collectionManager;
    private Movie argument;


    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public AddIfMin(String name, String description) {
        super(name, description);
        collectionManager = null;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public void setArgument(Movie argument) {
        this.argument = argument;
    }

    /**
     * Метод считывает id, передаваемый в качестве аргумента. Если id введён неверно, запрашивает повторное введение, пока не сможет распознать данные.
     * После создаёт объект типа Movie, заполняет его случайными данными или данными, введёнными пользователем (как решит пользователь),
     * Добавляет объект в коллекцию, если переданный id в качестве аргумента меньше минимального имеющегося в коллекции.
     *
     * @return Возвращает True при выполнении программы
     * @throws IOException
     */
    @Override
    public boolean exec() throws IOException {
            Module.addMessage(collectionManager.addElementIfLowerMin(argument));
            return true;
    }
}