package data;

public class Coordinates {
    private Double x;
    private Integer y;

    public Coordinates(Double x, Integer y){
        this.x=x;
        this.y=y;
    }

    public Coordinates(){}

    public Double getX(){
            return x;
    }

    public Integer getY(){
        return y;
    }

    @Override
    public String toString(){
        return getX().toString()+"(x), "+getY().toString()+"(y)";
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public int hashCode(){
        return (int) (getX()+getY());
    }
}
