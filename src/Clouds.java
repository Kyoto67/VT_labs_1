public class Clouds extends ObjectsFromStory implements Storytelling, Flying{
    public Clouds() throws Exception {
        setName("Тучка");
        addCondition(Status.PLUMP);
        addCondition(Status.SOFT);
        storyTelling();
    }

    public void storyTelling() throws Exception{
        System.out.println(getName() + " взлетает из шляпы и мягко парит в воздухе.");
        Thread.sleep(1000);
    }

    public void Flying() throws Exception{
        System.out.println(getName() + " выплывает на веранду и повисает над самой землёй.");
        Thread.sleep(1000);
    }
}