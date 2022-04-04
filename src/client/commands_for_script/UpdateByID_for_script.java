package client.commands_for_script;

import client.commands.AbstractCommand;
import client.data.*;
import client.util.Asker;
import server.exceptions.IncompleteData;
import server.util.CollectionManager;
import server.util.ScriptManager;

import java.util.Date;

public class UpdateByID_for_script extends AbstractCommand {

    private CollectionManager collectionManager;
    private final ScriptManager scriptManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public UpdateByID_for_script(String name, String description, ScriptManager scriptManager) {
        super(name, description);
        this.scriptManager = scriptManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод конвертирует аргумент в номер id элемента, который нужно обновить. Если номер введён неверно или в коллекции нет элемента с таким id, запрашивает повторный ввод.
     * Запрашивает у пользователя данные обновлённого объекта, если он хочет ввести их вручную, или заполняет автоматически случайными значениями.
     * Вызывает у пользователя замену элемента по номеру ID.
     *
     * @param argument номер id элемента, который необходимо обновить.
     * @return Возвращает True при выполнении.
     * @see CollectionManager#checkMatchingID(long)
     * @see Asker
     * @see CollectionManager#replaceElementByID(Movie, long)
     */
    @Override
    public boolean exec(String argument) {
        try {
            long id = Long.parseLong(argument);
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
            newMovie.setId(id);
            collectionManager.replaceElementByID(newMovie, id);
        } catch (IncompleteData e) {
            System.out.println(e.getMessage() + " Skip update_by_id.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable client.data. Skip add.");
            scriptManager.rollScriptForNextCommand();
            return false;
        }
        return true;
    }

}
