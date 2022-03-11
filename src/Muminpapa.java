import java.util.ArrayList;

//класс Мумипапы, наследуется от Mumintroll, имплементирует интерфейсы для работы со статусом
public class Muminpapa extends Mumintroll implements InterfaceForChooseStatus, StatusUse{

    //объявление приватных полей класса
    private boolean isLucky;
    private int HowLongAHatHasBeenOnHead;
    private int TimeForChangeStatus;
    private final ArrayList<Status> StatusArrayList = new ArrayList<>();

    //конструктор класса, устанавливается удача и время нахождения
    //шляпы на голове, необходимое для серьёзных изменений
    public Muminpapa(){
        setName("Мумми-папа");
        setLucky(true);
        setTimeForChangeStatus(3000);
    }

    //метод надевания шляпы, передаваемой ему, на голову
    public void PutAHat(HatOfWizard hat) throws Exception {
        System.out.println(getName() + " надевает шляпу Волшебника.");
        //вызов метода у шляпы, который меняет количество объектов в ней
        hat.changeCountObjects(1);
        //проверка на удачу
        if (getLucky()) {
            System.out.print("Шляпа ему не подошла. ");
            System.out.println(getName() + " носит шляпу непродолжительное время. ");
            //устанавливает значение времени, сколько он носил шляпу
            setHowLongAHatHasBeenUsed(500);
            Thread.sleep(getHowLongAHatHasBeenUsed());
            System.out.println(getName() + " снимает шляпу. ");
            //снова изменяет кол-во объектов в шляпе
            hat.changeCountObjects(-1);
        }
        //если не повезло, то всё идёт не по плану, и история останавливается
        else {
            System.out.println("Мумми-папе не повезло, через какое-то время он превратится во что-то неизвестное.");
            Thread.currentThread().interrupt();
        }
    }

    //реализация абстрактного метода взаимодействия со шляпой
    @Override
    public void SomethingIsGoingOn(HatOfWizard hat) throws Exception {
        System.out.println("Проходит какое-то время, Мумми-папа выбрасывает скорлупки в шляпу...");
        hat.changeCountObjects(5);
        Thread.sleep(500);
    }

    //реализация абстрактного метода крика
    @Override
    public void Shout() {
        System.out.println(getName() + " кричит:\"Вперёд!\"");
    }

    //реализация вспомогательных методов для работы со статусом и полем удачи
    public void addStatus (Status c){
        this.StatusArrayList.add(c);
    }
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
    protected void setLucky(boolean luck){
        this.isLucky=luck;
    }
    protected boolean getLucky(){
        return this.isLucky;
    }
    @Override
    public Object usingStatus() {
        if (checkForSeriouslyEffects()) {
            System.out.println("происходят серьёзные изменения...");
            return this;
        } else {
            addStatus(Status.HEADACHE);
            System.out.println(getName() + " ощущает " + this.toString() + " .");
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
