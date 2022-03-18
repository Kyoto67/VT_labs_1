package commands_for_script;

import commands.AbstractCommand;
import data.*;
import exceptions.IncompleteData;
import util.Asker;
import util.CollectionManager;
import util.CommandManager;

import java.util.Date;

public class UpdateByID_for_script extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param collectionManager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public UpdateByID_for_script(String name, String description, CollectionManager collectionManager, CommandManager commandManager) {
        super(name, description);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
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
            newMovie.setId(id);
            collectionManager.replaceElementByID(newMovie, id);
        } catch (IncompleteData e){
            System.out.println(e.getMessage()+" Skip update_by_id.");
            return false;
        } catch (Exception e) {
            System.out.println("Unreadable data. Skip add.");
            commandManager.rollScriptForNextCommand();
            return false;
        }
        return true;
    }

}
