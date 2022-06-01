package com.example.vt_labs_1.data;

import java.io.Serializable;

public enum Country implements Serializable {
    GERMANY,
    NORTH_KOREA,
    JAPAN;

    public static Country fromInt(int value) {
        for (Country country : Country.values()) {
            if (value == country.ordinal()) {
                return country;
            }
        }
        return null;
    }
}
