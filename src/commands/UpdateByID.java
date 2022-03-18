package commands;

import data.Coordinates;
import data.Location;
import data.Movie;
import data.Person;
import util.Asker;
import util.CollectionManager;
import util.GeneratingRandomInfo;

import java.util.Date;

public class UpdateByID extends AbstractCommand{
    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public UpdateByID(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    /**
     * Метод конвертирует аргумент в номер id элемента, который нужно обновить. Если номер введён неверно или в коллекции нет элемента с таким id, запрашивает повторный ввод.
     * Запрашивает у пользователя данные обновлённого объекта, если он хочет ввести их вручную, или заполняет автоматически случайными значениями.
     * Вызывает у пользователя замену элемента по номеру ID.
     * @param argument номер id элемента, который необходимо обновить.
     * @return Возвращает True при выполнении.
     * @see CollectionManager#checkMatchingID(long)
     * @see Asker
     * @see CollectionManager#replaceElementByID(Movie, long)
     */
    @Override
    public boolean exec(String argument) {
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
            System.out.println("Введён неверный формат id, повторите ввод.");
            argForParse=Asker.askIDForExec();
            }
        }
        if (manager.checkMatchingID(id)) {
            Movie newMovie = new Movie();
            if (Asker.askRandomMovie()) {
                newMovie.setName(Asker.askMovieName());
                newMovie.setGenre(Asker.askGenre());
                newMovie.setMpaaRating(Asker.askRating());
                newMovie.setCoordinates(new Coordinates(Asker.askCoordinatesX(), Asker.askCoordinatesY()));
                newMovie.setCreationDate(new Date());
                newMovie.setOscarsCount(Asker.askOscarsCount());
                if (Asker.askPerson()) {
                    Person director = new Person();
                    director.setName(Asker.askDirectorName());
                    director.setHeight(Asker.askDirectorHeight());
                    director.setEyeColor(Asker.askDirectorEyeColor());
                    director.setHairColor(Asker.askDirectorHairColor());
                    director.setNationality(Asker.askDirectorCountry());
                    if (Asker.askLocation()) {
                        director.setLocation(new Location(Asker.askLocationX(), Asker.askLocationY(), Asker.askLocationZ(), Asker.askLocationName()));
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
            manager.replaceElementByID(newMovie, id);
            return true;
        } else {
            System.out.println("Объект с таким id отсутствует в коллекции.");
            return false;
        }
    }

}
