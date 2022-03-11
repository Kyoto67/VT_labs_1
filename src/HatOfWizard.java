public class HatOfWizard {

    private int CountObjects;

    public HatOfWizard() {
        setCountObjects(0);
        reminder();
    }

    public void setCountObjects(int countObjects) {
        this.CountObjects = countObjects;
    }

    public int getCountObjects() {
        return CountObjects;
    }

    public void changeCountObjects(int i) {
        this.CountObjects = CountObjects + i;
    }

    private void reminder() {
        System.out.println("Всякая вещь, если она достаточно долго пролежит в шляпе Волшебника, превращается в нечто совершенно иное - и никогда нельзя знать заранее, во что именно.");
    }

    void EmptyHat() {
        if (getCountObjects() == 0) {
            System.out.println("Шляпа пуста.");
        } else {
            System.out.println("Шляпа заполнена.");
        }
    }
}