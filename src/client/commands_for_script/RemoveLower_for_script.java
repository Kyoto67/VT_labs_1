package client.commands_for_script;

import client.commands.AbstractCommand;
import client.data.*;
import client.exceptions.IncompleteData;
import client.util.Asker;
import client.util.CommandManager;

import java.io.IOException;
import java.util.Date;

public class RemoveLower_for_script extends AbstractCommand {

    private CollectionManager collectionManager;
    private final CommandManager commandManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param commandManager    сущность, считывающая построчно данные из скрипта.
     */
    public RemoveLower_for_script(String name, String description, CommandManager commandManager) {
        super(name, description);
        this.commandManager = commandManager;
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
     * @see Asker#askIDForExec()
     * @see CollectionManager#removeAllLower(Movie)
     */
    @Override
    public boolean exec(String argument) throws IOException {
        try {
            Movie movieForCompare = new Movie();
            movieForCompare.setName(commandManager.getNextLineFromScript());
            movieForCompare.setGenre(MovieGenre.valueOf(commandManager.getNextLineFromScript()));
            movieForCompare.setMpaaRating(MpaaRating.valueOf(commandManager.getNextLineFromScript()));
            movieForCompare.setCoordinates(new Coordinates(Double.parseDouble(commandManager.getNextLineFromScript()), Integer.parseInt(commandManager.getNextLineFromScript())));
            movieForCompare.setCreationDate(new Date());
            movieForCompare.setOscarsCount(Long.parseLong(commandManager.getNextLineFromScript()));
            Person director = new Person();
            director.setName(commandManager.getNextLineFromScript());
            director.setHeight(Double.parseDouble(commandManager.getNextLineFromScript()));
            director.setEyeColor(Color.valueOf(commandManager.getNextLineFromScript()));
            director.setHairColor(Color.valueOf(commandManager.getNextLineFromScript()));
            director.setNationality(Country.valueOf(commandManager.getNextLineFromScript()));
            director.setLocation(new Location(Double.parseDouble(commandManager.getNextLineFromScript()), Double.parseDouble(commandManager.getNextLineFromScript()), Double.parseDouble(commandManager.getNextLineFromScript()), (commandManager.getNextLineFromScript())));
            movieForCompare.setDirector(director);
            collectionManager.removeAllGreater(movieForCompare);
        } catch (IncompleteData e) {
            System.out.println(e.getMessage() + " Skip remove_lower.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable client.data. Skip remove_lower.");
            commandManager.rollScriptForNextCommand();
            return false;
        }
        return true;
    }
}
