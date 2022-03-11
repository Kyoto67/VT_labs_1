public class Muminpapa extends Mumintroll implements Storytelling, Flying{

    public Muminpapa(){
        setName("Мумми-папа");
        setLucky(true);
    }

    public void PutAHat() throws Exception {
        System.out.println(getName() + " надевает шляпу Волшебника.");
        if (getLucky()) {
            System.out.print("Шляпа ему не подошла. ");
            System.out.println(getName() + " носит шляпу непродолжительное время. ");
            setHowLongAHatHasBeenUsed(1000);
            Thread.sleep(getHowLongAHatHasBeenUsed());
        }
    }

    public void usingCondition() throws Exception {
        if (checkForSeriouslyEffects()) {
            System.out.println("происходят серьёзные изменения...");
        } else {
            addCondition(Status.HEADACHE);
            System.out.println(getName() + " ощущает " + Messages.getMessage(Status.HEADACHE) + " .");
        }
        Thread.sleep(1000);
    }

    public void storyTelling() throws Exception {
        System.out.println("Проходит какое-то время, Мумми-папа выбрасывает скорлупки в шляпу...");
        Thread.sleep(1000);
    }

    public void Flying() throws Exception{
        System.out.println(getName() + " оседлал тучку. ");

    }
}
