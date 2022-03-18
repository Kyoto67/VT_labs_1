package data;

import java.util.Date;

public class Movie {

    private long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private long oscarsCount;
    private MovieGenre genre;
    private MpaaRating mpaaRating;
    private Person director;

    public Movie(long id, String name, Coordinates coordinates, Date creationDate, long oscarsCount,
                 MovieGenre genre, MpaaRating mpaaRating, Person director) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }

    public Movie() {
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Идентификатор: " + getId() + ". \n" + "Название фильма: " + getName() + ". \n" + "Координаты: " + coordinates + ". \n" + "Дата создания: " + creationDate + ". \n"
                + "Количество оскаров: " + oscarsCount + ". \n" + "Жанр: " + genre + ". \n" + "Возрастной рейтинг: " + mpaaRating + ". \n"
                + "Режиссёр: \n" + director + "\n"
                + "hashCode: " + this.hashCode()+". ";
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setOscarsCount(long oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getOscarsCount() {
        return oscarsCount;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public Person getDirector() {
        return director;
    }

    @Override
    public int hashCode(){
        return getCoordinates().hashCode()+getDirector().hashCode()+getName().length();
    }
}
