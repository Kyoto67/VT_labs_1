package com.example.vt_labs_1.data;

import com.example.vt_labs_1.exceptions.IncorrectData;
import com.example.vt_labs_1.exceptions.NonRealisticData;
import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private double height;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;
    private Location location;

    public Person(String name, double height, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "    Имя: " + name + ". \n" + "  Рост: " + height + ". \n" + "   Цвет глаз: " + eyeColor + ". \n" + "    Цвет волос: "
                + hairColor + ". \n" + "   Национальность: " + nationality + ". \n" + "    Локация: " + location;
    }

    public double getHeight() {
        return height;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IncorrectData {
        if (name == null || name.equals("")) {
            throw new IncorrectData("Ur director_name null or incorrect");
        } else {
            this.name = name;
        }
    }

    public void setHeight(Double height) throws IncorrectData, NonRealisticData {
        if (height == null) {
            throw new IncorrectData("director_height is null.");
        } else {
            if (height <= 0 | height > 220) {
                throw new NonRealisticData("Введёны фантастический рост режиссёра.");
            }
        }
        this.height = height;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(Color hairColor) throws IncorrectData {
        if (hairColor == null) {
            throw new IncorrectData("Ur director_hairColor is null.");
        }
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) throws IncorrectData {
        if (nationality == null){
            throw new IncorrectData("Ur director_nationality is null.");
        }
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        if (!(location == null)) {
            return getLocation().hashCode() + getName().length();
        } else {
            return getName().length();
        }
    }
}
