import java.util.ArrayList;

//класс тучек, наследуется от ObjectsFromStory
public class Clouds extends ObjectsFromStory{

    //список статусов
    private ArrayList<Status> StatusArrayList = new ArrayList<>();
    //поле для наездника облака
    private Mumintroll rider = null;

    //конструктор класса, устанавливает имя и передаваемые
    //при создании объекта цвет и действующие эффекты, вызывает метод
    public Clouds(String color, Status s1, Status s2) throws Exception {
        setName("Тучка");
        setColor(color);
        StatusArrayList.add(s1);
        StatusArrayList.add(s2);
        storyTelling();
    }

    //реализация абстрактного метода
    @Override
    public void storyTelling() throws Exception{
        System.out.println(getName() + " взлетает из шляпы и мягко парит в воздухе.");
        Thread.sleep(500);
    }

    //метод, изменяющий место действия объекта и уменьшает счётчик количества объектов в шляпе, которую получает
    public void GetOutFromHat(HatOfWizard hat) throws Exception{
        System.out.println(getName() + " выплывает на веранду и повисает над самой землёй.");
        hat.changeCountObjects(-1);
        placesetter.setPlace(PLACES.GARDEN);
        System.out.println(getName() + " ждёт...");
        Thread.sleep(500);
    }

    //переопределение toString на возврат поля name объекта
    @Override
    public String toString(){
        return getName();
    }

    //метод для оседлания облака
    public void riding(Mumintroll troll) {
        //проверка на то, что облако никем не занято
        if (checkRider()){
        //полю rider присваивается ссылка на тролля-наездника
        rider = troll;
        System.out.println(getRiderName()+ " оседлал " + getName());
        }
        //если занято, то выбрасывается uncheked exception
        else {
            throw new CloudIsFullLoadException(troll.getName() +" пробует оседлать "+ getName() + ", но она занята. ");
        }
    }

    //возвращает true, если облако никем не занято
    //false, если наездник уже есть
    public boolean checkRider(){
        return (rider==null);
    }

    //метод возвращает имя наездника тучки
    public String getRiderName(){
        return rider.getName();
    }

    //метод, реализующий полёт на тучке
    public void Flying() throws BrokenCloudControlException {
        System.out.println(getRiderName() + " взлетает и пытается управлять тучкой. ");
        for (int i=0; i<5; i++){
            //вызывает 5 раз метод Move у вложенного класса Flying
            Flying.Move();
            }
        rider.Shout();
        }

    //вложенный класс для управления полётом
    private static class Flying {
        //метод для определения движения тучки
        public static void Move() throws BrokenCloudControlException {
            //локальный класс с переменной, содержащей рандомное число
            class MoveNumber {
                //private поле с числом
                private int move;
                //конструктор класса, устанавливает рандомное значение полю move
                MoveNumber(){
                    this.move=(int) (Math.random()*5 + 1);
                }
                //геттер на move
                int getNumber(){
                    return this.move;
                }
            }
            //создаёт объект локального класса
            MoveNumber number = new MoveNumber();
            //вызывает метод выбора движения, передаёт ему рандомное число объекта number
            MoveChoose(number.getNumber());
        }

        //метод, на основе получаемого числа, вызывающий движение тучки
        public static void MoveChoose(int move) throws BrokenCloudControlException {
            switch (move){
            case 1:
                up();
                break;
            case 2:
                down();
                break;
            case 3:
                forward();
                break;
            case 4:
                back();
                break;
            case 5:
                round();
                break;
            case 6:
                stop();
                break;
            }
        }

        //методы различных движений тучки, с определённым шансом выбрасывают проверяемое исключение ошибки управления
        public static void up() throws BrokenCloudControlException {
            if(Math.random()<0.05){
                throw new BrokenCloudControlException(" не справляется с управлением, падает и разбивается насмерть. ");
            } else {
                System.out.println("Взлетает выше. ");
            }
        }
        public static void down() throws BrokenCloudControlException {
            if(Math.random()<0.05){
                throw new BrokenCloudControlException(" не справляется с управлением, падает и разбивается насмерть. ");
            } else {
                System.out.println("Опускается ниже. ");
            }
        }
        public static void forward() throws BrokenCloudControlException {
            if(Math.random()<0.05){
                throw new BrokenCloudControlException(" не справляется с управлением, падает и разбивается насмерть. ");
            } else {
                System.out.println("Ускоряется. ");
            }
        }
        public static void back() throws BrokenCloudControlException {
            if(Math.random()<0.05){
                throw new BrokenCloudControlException(" не справляется с управлением, падает и разбивается насмерть. ");
            } else {
                System.out.println("Даёт задний ход. ");
            }
        }
        public static void round() throws BrokenCloudControlException {
            if(Math.random()<0.05){
                throw new BrokenCloudControlException(" не справляется с управлением, падает и разбивается насмерть. ");
            } else {
                System.out.println("Поворачивает. ");
            }
        }
        public static void stop() throws BrokenCloudControlException {
            if(Math.random()<0.05){
                throw new BrokenCloudControlException(" не справляется с управлением, падает и разбивается насмерть. ");
            } else {
                System.out.println("Останавливается. ");
            }
        }
    }
}