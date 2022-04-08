package common.commands;

import common.commands.AbstractCommand;
import common.data.*;
import common.exceptions.IncompleteData;
import server.util.CollectionManager;
import server.util.ScriptManager;

import java.io.IOException;
import java.util.Date;

public class RemoveLower_for_script extends AbstractCommand {

    private CollectionManager collectionManager;
    private final ScriptManager scriptManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param scriptManager    сущность, считывающая построчно данные из скрипта.
     */
    public RemoveLower_for_script(String name, String description, ScriptManager scriptManager) {
        super(name, description);
        this.scriptManager = scriptManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Вызывает удаление всех элементов коллекции, чей id меньше переданного в качестве аргумента программе.
     *
     * @param argument номер id, элементы с меньшим которого должны быть удалены.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#removeAllLower(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        try {
            Movie movieForCompare = new Movie();
            movieForCompare.setName(scriptManager.getNextLineFromScript());
            movieForCompare.setGenre(MovieGenre.valueOf(scriptManager.getNextLineFromScript()));
            movieForCompare.setMpaaRating(MpaaRating.valueOf(scriptManager.getNextLineFromScript()));
            movieForCompare.setCoordinates(new Coordinates(Double.parseDouble(scriptManager.getNextLineFromScript()), Integer.parseInt(scriptManager.getNextLineFromScript())));
            movieForCompare.setCreationDate(new Date());
            movieForCompare.setOscarsCount(Long.parseLong(scriptManager.getNextLineFromScript()));
            Person director = new Person();
            director.setName(scriptManager.getNextLineFromScript());
            director.setHeight(Double.parseDouble(scriptManager.getNextLineFromScript()));
            director.setEyeColor(Color.valueOf(scriptManager.getNextLineFromScript()));
            director.setHairColor(Color.valueOf(scriptManager.getNextLineFromScript()));
            director.setNationality(Country.valueOf(scriptManager.getNextLineFromScript()));
            director.setLocation(new Location(Double.parseDouble(scriptManager.getNextLineFromScript()), Double.parseDouble(scriptManager.getNextLineFromScript()), Double.parseDouble(scriptManager.getNextLineFromScript()), (scriptManager.getNextLineFromScript())));
            movieForCompare.setDirector(director);
            collectionManager.removeAllGreater(movieForCompare);
        } catch (IncompleteData e) {
            System.out.println(e.getMessage() + " Skip remove_lower.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable common.data. Skip remove_lower.");
            scriptManager.rollScriptForNextCommand();
            return false;
        }
        return true;
    }
}
