package commands;

import data.Coordinates;
import data.Location;
import data.Movie;
import data.Person;
import util.Asker;
import util.CollectionManager;
import util.GeneratingRandomInfo;

import java.util.Date;

/**
 * Команда добавления объекта в коллекцию.
 */
public class Add extends AbstractCommand {

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public Add(String name, String description, CollectionManager manager){
        super(name,description);
        this.manager=manager;
    }

    /**
     * Метод создаёт объект типа Movie, спрашивает у пользователя, хочет ли он ввести данные самостоятельно или заполнить случайными значениями.
     * Запрашивает данные, если пользователь решает ввести самостоятельно
     * @param argument не требует аргументов, передаётся пустая строка.
     * @return Возвращает True при выполнении.
     * @see Asker
     */
    @Override
    public boolean exec(String argument) {
        Movie newMovie = new Movie();
        if (Asker.askRandomMovie()){
            newMovie.setName(Asker.askMovieName());
            newMovie.setGenre(Asker.askGenre());
            newMovie.setMpaaRating(Asker.askRating());
            newMovie.setCoordinates(new Coordinates(Asker.askCoordinatesX(),Asker.askCoordinatesY()));
            newMovie.setCreationDate(new Date());
            newMovie.setOscarsCount(Asker.askOscarsCount());
            if (Asker.askPerson()){
                Person director = new Person();
                director.setName(Asker.askDirectorName());
                director.setHeight(Asker.askDirectorHeight());
                director.setEyeColor(Asker.askDirectorEyeColor());
                director.setHairColor(Asker.askDirectorHairColor());
                director.setNationality(Asker.askDirectorCountry());
                if (Asker.askLocation()){
                    director.setLocation(new Location(Asker.askLocationX(),Asker.askLocationY(),Asker.askLocationZ(),Asker.askLocationName()));
                } else {
                    director.setLocation(null);
                }
            newMovie.setDirector(director);
            } else {
                newMovie.setDirector(null);
            }
        } else {
            newMovie = GeneratingRandomInfo.generateOneObject();
        }
        manager.addElement(newMovie);
        return true;
    }
}
