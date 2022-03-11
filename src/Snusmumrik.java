//класс Снусмумрик наследуется от Mumintroll
public class Snusmumrik extends Mumintroll{

    //конструктор класса.
    //устанавливается имя и место появления персонажа
    public Snusmumrik() {
        setName("Снусмумрик");
        placesetter.setPlace(PLACES.LIVINGROOM);
    }

    //метод изменяет место нахождения у шляпы, которую получает
    @Override
    public void SomethingIsGoingOn(HatOfWizard hat){
        System.out.println("Снусмумрик взял шляпу и поставил её на пол между комодом и кухонной дверью.");
        hat.placesetter.setPlace(PLACES.LIVINGROOM);
    }

    //крики Снусмумрика
    @Override
    public void Shout() {
        System.out.println(getName() + " кричит:\"А ну давай!\"");
    }

    //Снусмумрик трогает облака
    @Override
    protected void TouchObject(Object o){
        System.out.println(getName() + " толкает " + o.toString());
        System.out.println("Она проплывает небольшое расстояние. ");
    }
}
