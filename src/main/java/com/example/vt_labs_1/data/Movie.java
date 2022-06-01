package com.example.vt_labs_1.data;

import com.example.vt_labs_1.exceptions.IncorrectData;
import com.example.vt_labs_1.utility.User;
import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable, Comparable {

    private long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private long oscarsCount;
    private MovieGenre genre;
    private MpaaRating mpaaRating;
    private Person director;
    private User owner;

    public Movie(long id, String name, Coordinates coordinates, Date creationDate, long oscarsCount,
                 MovieGenre genre, MpaaRating mpaaRating, Person director, User owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
        this.owner = owner;
    }

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

    public void setOwner(User owner){
        this.owner=owner;
    }

    public User getOwner() {
        return owner;
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
                + "hashCode: " + this.hashCode() + "\n"
                + "Владелец объекта: " + this.owner.getUsername()
                + ". \n";
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setId(Long id) throws IncorrectData {
        if (!(id==null) && (id>0)){
            this.id = id;
        } else {
            throw new IncorrectData("id <= 0.");
        }
    }

    public void setName(String name) throws IncorrectData {
        if(name==null || name.equals("")){
            throw new IncorrectData("Incorrect name field.");
        } else{
            this.name = name;
        }
    }

    public void setCoordinates(Coordinates coordinates) throws IncorrectData {
        if(coordinates==null){
            throw new IncorrectData("Ur coordinates is null.");
        } else {
            this.coordinates = coordinates;
        }
    }

    public void setCreationDate(Date creationDate) throws IncorrectData {
        if(creationDate==null){
            throw new IncorrectData("Ur date is null.");
        } else {
            this.creationDate = creationDate;
        }
    }

    public void setOscarsCount(Long oscarsCount) throws IncorrectData {
        if (oscarsCount==null || oscarsCount<=0){
            throw new IncorrectData("Ur oscarscount incorrect.");
        } else {
            this.oscarsCount = oscarsCount;
        }
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
    public int hashCode() {
        if (!(director == null)) {
            return getCoordinates().hashCode() + getDirector().hashCode() + getName().length();
        } else {
            return getCoordinates().hashCode() + getName().length();
        }
    }

    @Override
    public int compareTo(Object o) {
        return this.hashCode()-o.hashCode();
    }
}
