public class Story {
    public static void main(String[] args) throws Exception {
        HatOfWizard hat = new HatOfWizard();
        Muminpapa papa = new Muminpapa();
        papa.PutAHat();
        papa.storyTelling();
        Eggshell[] eggshells = new Eggshell[5];
        for (int i = 0; i < eggshells.length; i++) {
            eggshells[i] = new Eggshell();
        }
        hat.changeCountObjects(5);
        papa.usingCondition();
        for (int i = 0; i < eggshells.length; i++) {
            eggshells[i].usingCondition();
        }
        hat.EmptyHat();
        Clouds[] clouds = new Clouds[5];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Clouds();
        }
        for (int i = 0; i< clouds.length; i++){
            clouds[i].Flying();
            hat.changeCountObjects(-1);
        }
        hat.EmptyHat();
    }
}