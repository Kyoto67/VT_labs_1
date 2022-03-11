public class Messages {

    public Messages() {}

    public static String getMessage(Status c){
        return switch (c) {
            case SOFT -> "мягкая";
            case PLUMP -> "пухлая";
            case HEADACHE -> "головную боль";
        };
    }
}
