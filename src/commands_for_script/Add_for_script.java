package commands_for_script;

import commands.AbstractCommand;
import data.*;
import exceptions.IncompleteData;
import util.Asker;
import util.CollectionManager;
import util.CommandManager;

import java.util.Date;

/**
 * Команда добавления объекта в коллекцию.
 */
public class Add_for_script extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param collectionManager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     * @param commandManager    сущность, считывающая построчно данные из скрипта.
     */
    public Add_for_script(String name, String description, CollectionManager collectionManager, CommandManager commandManager) {
        super(name, description);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    /**
     * Метод создаёт объект типа Movie, спрашивает у пользователя, хочет ли он ввести данные самостоятельно или заполнить случайными значениями.
     * Запрашивает данные, если пользователь решает ввести самостоятельно
     *
     * @param argument не требует аргументов, передаётся пустая строка.
     * @return Возвращает True при выполнении.
     * @see Asker
     */
    @Override
    public boolean exec(String argument) {
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
            collectionManager.addElement(newMovie);
        } catch (IncompleteData e) {
            System.out.println(e.getMessage() + " Skip add.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable data. Skip add.");
            commandManager.rollScriptForNextCommand();
            return false;
        }
        return true;
    }
}
