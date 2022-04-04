package client.commands_for_script;

import client.commands.AbstractCommand;
import client.data.*;
import client.util.Asker;
import server.exceptions.IncompleteData;
import server.util.CollectionManager;
import server.util.ScriptManager;

import java.util.Date;

/**
 * Команда добавления объекта в коллекцию.
 */
public class Add_for_script extends AbstractCommand {

    private CollectionManager collectionManager;
    private final ScriptManager scriptManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public Add_for_script(String name, String description, ScriptManager scriptManager) {
        super(name, description);
        this.scriptManager = scriptManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
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
            newMovie.setName(scriptManager.getNextLineFromScript());
            newMovie.setGenre(MovieGenre.valueOf(scriptManager.getNextLineFromScript()));
            newMovie.setMpaaRating(MpaaRating.valueOf(scriptManager.getNextLineFromScript()));
            newMovie.setCoordinates(new Coordinates(Double.parseDouble(scriptManager.getNextLineFromScript()), Integer.parseInt(scriptManager.getNextLineFromScript())));
            newMovie.setCreationDate(new Date());
            newMovie.setOscarsCount(Long.parseLong(scriptManager.getNextLineFromScript()));
            Person director = new Person();
            director.setName(scriptManager.getNextLineFromScript());
            director.setHeight(Double.parseDouble(scriptManager.getNextLineFromScript()));
            director.setEyeColor(Color.valueOf(scriptManager.getNextLineFromScript()));
            director.setHairColor(Color.valueOf(scriptManager.getNextLineFromScript()));
            director.setNationality(Country.valueOf(scriptManager.getNextLineFromScript()));
            director.setLocation(new Location(Double.parseDouble(scriptManager.getNextLineFromScript()), Double.parseDouble(scriptManager.getNextLineFromScript()), Double.parseDouble(scriptManager.getNextLineFromScript()), (scriptManager.getNextLineFromScript())));
            newMovie.setDirector(director);
            collectionManager.addElement(newMovie);
        } catch (IncompleteData e) {
            System.out.println(e.getMessage() + " Skip add.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable client.data. Skip add.");
            scriptManager.rollScriptForNextCommand();
            return false;
        }
        System.out.println("Команда add выполнена.");
        return true;
    }
}
