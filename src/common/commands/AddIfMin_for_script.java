package common.commands;

import common.commands.AbstractCommand;
import common.data.*;
import common.exceptions.IncompleteData;
import server.util.CollectionManager;
import server.util.ScriptManager;

import java.io.IOException;
import java.util.Date;

public class AddIfMin_for_script extends AbstractCommand {

    private CollectionManager collectionManager;
    private final ScriptManager scriptManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param scriptManager    сущность, считывающая построчно данные из скрипта.
     */
    public AddIfMin_for_script(String name, String description, ScriptManager scriptManager) {
        super(name, description);
        this.scriptManager = scriptManager;
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
     * @see CollectionManager#addElementIfLowerMin(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
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
            collectionManager.addElementIfLowerMin(newMovie);
        } catch (IncompleteData e) {
            System.out.println(e.getMessage() + " Skip add_if_min.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable common.data. Skip add_if_min.");
            scriptManager.rollScriptForNextCommand();
            return false;
        }
        System.out.println("Команда add выполнена.");
        return true;
    }
}
