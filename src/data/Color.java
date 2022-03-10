package data;

public enum Color {
    BLUE,
    ORANGE,
    WHITE,
    BROWN,
    GREEN,
    BLACK,
    YELLOW;

    public static Color fromInt(int value) {
        for (Color color : Color.values()) {
                if (value == color.ordinal()) {
                    return color;
                }
            }
        return null;
    }
}
