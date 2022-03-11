//абстрактный класс для всех муммитроллей
public abstract class Mumintroll{

    //private поля муммитролей
    private String name;
    private PLACES PLACE;

    //геттеры и сеттеры
    protected String getName(){
        return this.name;
    }
    protected void setName(String name){
        this.name=name;
    }
    protected PLACES getPLACE(){
        return PLACE;
    }

    //одинаковый для всех, кроме Снусмумрика метод "потрогать объект"
    //у Снусмумрика переопределён
    protected void TouchObject(Object o){
        System.out.println(name + " трогает " + o.toString());
    }

    //лямбда-выражение с использованием экземпляра функционального интерфейса
    Placesetter placesetter = (place) -> {
        PLACE = place;
        System.out.println(name + " перемещается в " + PLACE.getString());
    };

    //абстрактные для муммитроллей методы взаимодействия со шляпой и крика
    public abstract void SomethingIsGoingOn(HatOfWizard hat) throws Exception;
    public abstract void Shout();
}
