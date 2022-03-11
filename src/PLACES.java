//перечисляемые значения мест из истории
public enum PLACES {
    LIVINGROOM("Гостиная"),
    GARDEN("Сад"),
    KITCHEN("Кухня"),
    HAT("Шляпа");

    //private поле строки
    private final String string;

    //конструктор с присваиванием строки из enum
    //полю для дальнейшей её передачи вызывающим методам
    PLACES(String s) {
        this.string=s;
    }

    //метод для получения строки с переводом значения enum
    public String getString(){
        return string;
    }
}
