//класс Фрекенснорк, наследуется от Mumintroll
public class Frekensnork extends Mumintroll{

    //конструктор класса
    //устанавливается имя и место появления персонажа
    public Frekensnork(){
        setName("Фрекенснорк");
        placesetter.setPlace(PLACES.GARDEN);
    }

    //реализация абстрактного метода взаимодействия со шляпой
    @Override
    public void SomethingIsGoingOn(HatOfWizard hat){
        System.out.println(getName()+" прижимает " + hat.getName()+" к земле и запрыгивает на неё.");
    }

    //реализация абстрактного метода крика
    @Override
    public void Shout() {
        System.out.println(getName() + " кричит:\"А ну давай! Гоп!\"");
    }

}
