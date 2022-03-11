import java.util.ArrayList;

//класс яичных скорлупок, наследуется от ObjectsFromStory, имплементирует интерфейсы для работы со статусом объекта
public class Eggshell extends ObjectsFromStory implements InterfaceForChooseStatus, StatusUse{

    //объявления private полей для работы со статусом
    //(изначально поля и методы писались для муммипапы, а потом были лениво скопипащены, так что название кривоватое)
    private int HowLongAHatHasBeenOnHead;
    private int TimeForChangeStatus;
    private ArrayList<Status> StatusArrayList = new ArrayList<>();

    //конструктор класса
    //устанавливаются значения private полей имени,
    //времени, сколько необходимо пролежать скорлупкам в шляпе для того, чтобы произошли серьёзные изменения,
    //вызов метода
    public Eggshell() throws Exception {
        setTimeForChangeStatus(5000);
        setName("Яичная скорлупка");
        setColor("Белый");
        storyTelling();
    }

    //реализация абстрактного метода
    //изменение места действия скорлупок
    //задание времени, сколько они пролежали в шляпе
    protected void storyTelling() throws Exception{
        placesetter.setPlace(PLACES.HAT);
        System.out.println(getName() + " лежит в шляпе...");
        Thread.sleep(500);
        setHowLongAHatHasBeenUsed(5000);
    }

    //реализация вспомогательных методов для работы со статусом
    public void setHowLongAHatHasBeenUsed(int time){
        this.HowLongAHatHasBeenOnHead = time;
    }
    public int getHowLongAHatHasBeenUsed(){
        return this.HowLongAHatHasBeenOnHead;
    }
    public boolean checkForSeriouslyEffects() {
        return equals(this);
    }
    protected void setTimeForChangeStatus(int i){
        this.TimeForChangeStatus=i;
    }
    @Override
    public void addStatus (Status c){
        this.StatusArrayList.add(c);
    }
    @Override
    public Object usingStatus() throws Exception {
        //проверка, должны ли быть серьёзные изменения
        if (checkForSeriouslyEffects()) {
            //присваивание статуса мягкий и пухлый
            addStatus(Status.SOFT);
            addStatus(Status.PLUMP);
            System.out.println(getName() + " становится " + this.toString()+" ...");
            Thread.sleep(500);
            System.out.println(getName() + " увеличивается в размерах и превращается в тучку.");
            //создание и возврат объкта облака, в которое превращается скорлупка
            Clouds cloud = new Clouds(getColor(), StatusArrayList.get(0), StatusArrayList.get(1));
            return cloud;
        } else {
            //если серьёзных изменений не происходит, то скорлупка остаётся скорлупкой и метод возвращает её
            System.out.println("Ничего не происходит...");
            return this;
        }
    }

    //переопределение hashcode: возвращает разницу между временем взаимодействия со
    //шляпой, необходимым для серьёзных изменений, и реальным временем взаимодействия
    @Override
    public int hashCode() {
        return TimeForChangeStatus-HowLongAHatHasBeenOnHead;
    }

    //переопределение equals: сравнивает объект на наличие серьёзных изменений от взаимодействия со шляпой
    @Override
    public boolean equals(Object o){
        return o.hashCode()<=0;
    }

    //переопределение toString: возвращает строку переведённого списка эффектов объекта
    @Override
    public String toString(){
        switch (StatusArrayList.size()){
            case 1:{
                return Messages.getMessage(StatusArrayList.get(0));
            }
            case 2:{
                return Messages.getMessage(StatusArrayList.get(0))+" и "+Messages.getMessage(StatusArrayList.get(1));
            }
            case 3:{
                return Messages.getMessage(StatusArrayList.get(0))+", "+Messages.getMessage(StatusArrayList.get(1))+" и "+Messages.getMessage(StatusArrayList.get(2));
            }
            default:{
                return "здоров";
            }
        }
    }
}