public class Eggshell extends ObjectsFromStory implements Storytelling {

    public Eggshell() throws Exception {
        storyTelling();
    }

    public void storyTelling() throws Exception{
        System.out.println(getName() + " лежит в шляпе...");
        Thread.sleep(1000);
        setHowLongAHatHasBeenUsed(10000);
    }

    public void usingCondition() throws Exception {
        if (checkForSeriouslyEffects()) {
            addCondition(Status.SOFT);
            addCondition(Status.PLUMP);
            System.out.println(getName() + " становится " + Messages.getMessage(Status.SOFT) + " и " + Messages.getMessage(Status.PLUMP)+ " ...");
            Thread.sleep(1000);
            System.out.println(getName() + " увеличивается в размерах и превращается в тучку.");
        } else {
            System.out.println("Ничего не происходит...");
        }
    }
}