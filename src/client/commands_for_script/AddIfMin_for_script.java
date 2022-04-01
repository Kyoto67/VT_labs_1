package client.commands_for_script;

import client.commands.AbstractCommand;
import client.data.*;
import client.exceptions.IncompleteData;
import client.util.Asker;
import client.util.CommandManager;

import java.io.IOException;
import java.util.Date;

public class AddIfMin_for_script extends AbstractCommand {

    private CollectionManager collectionManager;
    private final CommandManager commandManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param commandManager    сущность, считывающая построчно данные из скрипта.
     */
    public AddIfMin_for_script(String name, String description, CommandManager commandManager) {
        super(name, description);
        this.commandManager = commandManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод считывает id, передаваемый в качестве аргумента. Если id введён неверно, запрашивает повторное введение, пока не сможет распознать данные.
     * После создаёт объект типа Movie, заполняет его случайными данными или данными, введёнными пользователем (как решит пользователь),
     * Добавляет объект в коллекцию, если переданный id в качестве аргумента меньше минимального имеющегося в коллекции.
     *
     * @param argument id нового объекта Movie
     * @return Возвращает True при выполнении программы
     * @throws IOException
     * @see Asker
     * @see CollectionManager#addElementIfLowerMin(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        try {
            Movie newMovie = new Movie();
            newMovie.setName(commandManager.getNextLineFromScript());
            newMovie.setGenre(MovieGenre.valueOf(commandManager.getNextLineFromScript()));
            newMovie.setMpaaRating(MpaaRating.valueOf(commandManager.getNextLineFromScript()));
            newMovie.setCoordinates(new Coordinates(Double.parseDouble(commandManager.getNextLineFromScript()), Integer.parseInt(commandManager.getNextLineFromScript())));
            newMovie.setCreationDate(new Date());
            newMovie.setOscarsCount(Long.parseLong(commandManager.getNextLineFromScript()));
            Person director = new Person();
            director.setName(commandManager.getNextLineFromScript());
            director.setHeight(Double.parseDouble(commandManager.getNextLineFromScript()));
            director.setEyeColor(Color.valueOf(commandManager.getNextLineFromScript()));
            director.setHairColor(Color.valueOf(commandManager.getNextLineFromScript()));
            director.setNationality(Country.valueOf(commandManager.getNextLineFromScript()));
            director.setLocation(new Location(Double.parseDouble(commandManager.getNextLineFromScript()), Double.parseDouble(commandManager.getNextLineFromScript()), Double.parseDouble(commandManager.getNextLineFromScript()), (commandManager.getNextLineFromScript())));
            newMovie.setDirector(director);
            collectionManager.addElementIfLowerMin(newMovie);
        } catch (IncompleteData e) {
            System.out.println(e.getMessage() + " Skip add_if_min.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable client.data. Skip add_if_min.");
            commandManager.rollScriptForNextCommand();
            return false;
        }
        return true;
    }
}
