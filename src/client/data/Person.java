package client.data;

public class Person {
    private String name;
    private double height;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;
    private Location location;

    public Person(String name, double height, Color eyeColor, Color hairColor, Country nationality, Location location){
        this.name=name;
        this.height=height;
        this.eyeColor=eyeColor;
        this.hairColor=hairColor;
        this.nationality=nationality;
        this.location=location;
    }

    public Person(){}

    @Override
    public String toString(){
        return "    Имя: "+name+". \n"+"  Рост: "+height+". \n"+"   Цвет глаз: "+eyeColor+". \n"+"    Цвет волос: "
                +hairColor+". \n"+"   Национальность: "+nationality+". \n"+"    Локация: "+location;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode(){
        if(!(location==null)){
            return getLocation().hashCode()+getName().length();
        } else {
            return getName().length();
        }
    }
}
