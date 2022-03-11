//первый вариант получения перевода enum значений
//(писался для третьей лабы, второй вариант для четвёртой см. PLACES)
public class Messages {

    public Messages() {}

    public static String getMessage(Status c){
        switch (c) {
            case SOFT: {
                return "мягкая";
            }
            case PLUMP: {
                return "пухлая";
            }
            case HEADACHE: {
                return "головную боль";
            }
        }
        return "ничего";
    }
}
