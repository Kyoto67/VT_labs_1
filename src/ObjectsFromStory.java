//абстрактный класс неживых объектов истории
public abstract class ObjectsFromStory{

    //private поля со значениями, которые есть у всех наследуемых объектов
    private String name;
    private String color;
    private PLACES PLACE;

    //protected геттеры и сеттеры private полей
    protected String getName(){
        return this.name;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected void setColor(String color){
        this.color = color;
    }
    protected String getColor(){
        return this.color;
    }
    protected PLACES getPLACE(){
        return PLACE;
    }

    //экземпляр анонимного класса placesetter
    Placesetter placesetter = new Placesetter() {
        @Override
        public void setPlace(PLACES place) {
            PLACE = place;
            System.out.println(name + " перемещается в " + PLACE.getString());
        }
    };

    //абстрактный для всех объектов метод
    protected abstract void storyTelling() throws Exception;
}