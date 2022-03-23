package commands_for_script;

import commands.AbstractCommand;
import data.*;
import exceptions.IncompleteData;
import util.Asker;
import util.CollectionManager;
import util.CommandManager;

import java.io.IOException;
import java.util.Date;

public class RemoveGreater_for_script extends AbstractCommand {

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
    public RemoveGreater_for_script(String name, String description, CollectionManager collectionManager, CommandManager commandManager) {
        super(name, description);
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Вызывает удаление всех элементов коллекции, чей id больше переданного в качестве аргумента программе.
     *
     * @param argument номер id, элементы с большим которого должны быть удалены.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see Asker#askIDForExec()
     * @see CollectionManager#removeAllGreater(Movie)
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
            System.out.println(e.getMessage() + " Skip remove_greater.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable data. Skip remove_greater.");
            commandManager.rollScriptForNextCommand();
            return false;
        }
        return true;
    }
}
