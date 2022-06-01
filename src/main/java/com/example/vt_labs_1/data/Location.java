package com.example.vt_labs_1.data;

import com.example.vt_labs_1.exceptions.IncorrectData;
import java.io.Serializable;
public class Location implements Serializable {
    private Double x;
    private double y;
    private Double z;
    private String name;

    public Location(Double x, double y, Double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Location() {
    }

    public Double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " ( Координаты: " + x.toString() + "(x), " + y + "(y), " + z.toString() + "(z) ). ";
    }

    public void setX(Double x) throws IncorrectData {
        if (x == null) {
            throw new IncorrectData("Location_x is null.");
        }
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(Double z) throws IncorrectData {
        if (z == null) {
            throw new IncorrectData("Location_z is null.");
        }
        this.z = z;
    }

    public void setName(String name) throws IncorrectData {
        if(!(name == null) && name.length()>233){
            throw new IncorrectData("Длина Location_name не должна быть больше 233.");
        }
        this.name = name;
    }

    @Override
    public int hashCode() {
        return (int) (getX() + getY() + getZ());
    }
}
