public class Story {
    //trows Exception пробрасывает в JVM InterruptException от метода Thread.sleep()
    public static void main(String[] args) throws Exception {
        //создание объекта шляпа
        HatOfWizard hat = new HatOfWizard();
        //создание объекта Муммипапа
        Muminpapa papa = new Muminpapa();
        //Муммипапа надевает шляпу
        papa.PutAHat(hat);
        //создание объекта Снусмумрик
        Snusmumrik snusmumrik = new Snusmumrik();
        //Снусмумрик ставит шляпу на пол
        snusmumrik.SomethingIsGoingOn(hat);
        //Муммипапа выбрасывает скорлупки в шляпу
        papa.SomethingIsGoingOn(hat);
        //создание массива скорлупок
        Eggshell[] eggshells = new Eggshell[5];
        //заполнение массива скорлупок объектами
        for (int i = 0; i < eggshells.length; i++) {
            eggshells[i] = new Eggshell();
        }
        //у Муммипапы разболелась голова
        papa.usingStatus();
        //создание массива облаков
        Clouds[] clouds = new Clouds[5];
        //скорлупки трансформируются и превращаются в облака,
        //создание объектов облаков и передача их в соответствующий массив
        for (int i = 0; i < eggshells.length; i++) {
            clouds[i] = (Clouds) eggshells[i].usingStatus();
        }
        //облака вылетают из шляпы
        for (int i = 0; i< clouds.length; i++){
            clouds[i].GetOutFromHat(hat);
        }
        //шляпа осталась пуста
        hat.EmptyHat();
        //создание объекта Фрекенснорк
        Frekensnork frekensnork = new Frekensnork();
        //все вышли в сад
        papa.placesetter.setPlace(PLACES.GARDEN);
        snusmumrik.placesetter.setPlace(PLACES.GARDEN);
        //все потрогали облака
        frekensnork.TouchObject(clouds[0]);
        snusmumrik.TouchObject(clouds[1]);
        papa.TouchObject(clouds[2]);
        //все оседлали облака
        clouds[0].riding(frekensnork);
        clouds[1].riding(snusmumrik);
        clouds[2].riding(papa);
        //цикл по всем облакам
        for (Clouds c: clouds) {
            //если на облаке есть наездник (облаков 5, героев 3, 2 облака пустые)
            if (!(c.checkRider())) {
                //муммитролли летают на облаках
                try {
                    c.Flying();
                } //обработка исключения, когда облако не слушаются наездника
                 catch (BrokenCloudControlException e) {
                    System.out.println(c.getRiderName() + e.getMessage());
                    //если один муммитролль падает и разбивается, остальные больше не летают, поэтому break
                    break;
                }
            }
        }
    }
}