package commands;

import data.Coordinates;
import data.Location;
import data.Movie;
import data.Person;
import util.Asker;
import util.CollectionManager;
import util.GeneratingRandomInfo;

import java.io.IOException;
import java.util.Date;

public class AddIfMin extends AbstractCommand{

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public AddIfMin(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    /**
     * Метод считывает id, передаваемый в качестве аргумента. Если id введён неверно, запрашивает повторное введение, пока не сможет распознать данные.
     * После создаёт объект типа Movie, заполняет его случайными данными или данными, введёнными пользователем (как решит пользователь),
     * Добавляет объект в коллекцию, если переданный id в качестве аргумента меньше минимального имеющегося в коллекции.
     * @param argument id нового объекта Movie
     * @return Возвращает True при выполнении программы
     * @throws IOException
     * @see Asker
     * @see CollectionManager#addElementIfIDLowerMin(Movie, long) 
     */
    @Override
    public boolean exec(String argument) throws IOException {
        boolean successParse = false;
        String argForParse = argument;
        long id=0;
        while (!successParse) {
            try {
                id = Long.valueOf(argForParse);
                while (!(id>0)){
                    throw new NumberFormatException();
                }
                successParse = true;
            } catch(NumberFormatException e){
                System.out.println("Введён неверный id, повторите ввод.");
                argForParse=Asker.askIDForExec();
            }
        }
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
        newMovie.setId(id);
        manager.addElementIfIDLowerMin(newMovie, id);
        return true;
    }
}
