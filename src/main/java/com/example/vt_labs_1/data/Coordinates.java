package com.example.vt_labs_1.data;

import com.example.vt_labs_1.exceptions.IncorrectData;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Double x;
    private Integer y;

    public Coordinates(Double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public Double getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return getX().toString() + "(x), " + getY().toString() + "(y)";
    }

    public void setX(Double x) throws IncorrectData {
        if (x == null || x <= -312) {
            throw new IncorrectData("Ur coordinates_x incorrect or null.");
        } else {
            this.x = x;
        }
    }

    public void setY(Integer y) throws IncorrectData {
        if (y == null || y<= -901){
            throw new IncorrectData("Ur coordinates_y incorrect or null.");
        } else {
            this.y = y;
        }
    }

    @Override
    public int hashCode() {
        return (int) (getX() + getY());
    }
}
