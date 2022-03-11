//класс шляпы волшебника, наследуется от ObjectsFromStory
public class HatOfWizard extends ObjectsFromStory{

    //private поле счётчика объектов в шляпе
    private int CountObjects;

    //конструктор класса
    //устанавливается имя, начальное количество объектов (0), запуск метода с выводом сообщения
    public HatOfWizard() {
        setName("Шляпа Волшебника");
        setCountObjects(0);
        storyTelling();
    }

    //вспомогательные методы для работы с private полем
    public void setCountObjects(int countObjects) {
        this.CountObjects = countObjects;
    }
    public int getCountObjects() {
        return CountObjects;
    }
    public void changeCountObjects(int i) {
        this.CountObjects = CountObjects + i;
    }

    //проверка на пустоту шляпы
    void EmptyHat() {
        if (getCountObjects() == 0) {
            System.out.println("Шляпа пуста.");
        } else {
            System.out.println("Шляпа заполнена.");
        }
    }

    //реализация абстрактного метода
    @Override
    protected void storyTelling(){
        System.out.println("Всякая вещь, если она достаточно долго пролежит в шляпе Волшебника, превращается в нечто совершенно иное - и никогда нельзя знать заранее, во что именно.");
    }
}