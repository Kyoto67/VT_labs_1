package common.data;

import java.io.Serializable;

public class Location implements Serializable {
    private Double x;
    private double y;
    private Double z;
    private String name;

    public Location(Double x, double y, Double z, String name){
        this.x=x;
        this.y=y;
        this.z=z;
        this.name=name;
    }

    public Location(){}

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
    public String toString(){
        return name+" ( Координаты: "+x.toString()+"(x), "+y+"(y), "+z.toString()+"(z) ). ";
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode(){
        return (int) (getX()+getY()+getZ());
    }
}
