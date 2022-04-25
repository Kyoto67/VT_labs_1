package data;

import java.io.Serializable;

public enum Color implements Serializable {
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
