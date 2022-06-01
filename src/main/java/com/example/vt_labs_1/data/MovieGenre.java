package com.example.vt_labs_1.data;

import java.io.Serializable;

public enum MovieGenre implements Serializable {
    ACTION,
    DRAMA,
    ADVENTURE;

    public static MovieGenre fromInt(int value) {
        for (MovieGenre movieGenre : MovieGenre.values()) {
            if (value == movieGenre.ordinal()) {
                return movieGenre;
            }
        }
        return null;
    }
}
