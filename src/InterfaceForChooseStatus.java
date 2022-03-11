//интерфейс с методами, определяющими, насколько серьёзными
//должны быть изменения объекта от взаимодействия со шляпой
public interface InterfaceForChooseStatus {
    void setHowLongAHatHasBeenUsed(int time);
    int getHowLongAHatHasBeenUsed();
    boolean checkForSeriouslyEffects();
}