package commands_for_script;

import commands.AbstractCommand;
import data.*;
import sun.awt.X11.XSystemTrayPeer;
import util.Asker;
import util.CollectionManager;
import util.CommandManager;
import util.GeneratingRandomInfo;

import java.io.IOException;
import java.util.Date;

public class AddIfMin_for_script extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    /**
     * конструктор
     *
     * @param name
     * @param description
     * @param collectionManager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public AddIfMin_for_script(String name, String description, CollectionManager collectionManager, CommandManager commandManager) {
        super(name, description);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
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
     * @see CollectionManager#addElementIfIDLowerMin(Movie, long)
     */
    @Override
    public boolean exec(String argument) throws IOException {
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
            collectionManager.addElementIfIDLowerMin(newMovie, id);
        } catch (Exception e){
            System.out.println("Кривой скрипт");
        }
        return true;
    }
}
