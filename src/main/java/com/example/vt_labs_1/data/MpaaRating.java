package com.example.vt_labs_1.data;

import java.io.Serializable;

public enum MpaaRating implements Serializable {
    PG,
    PG_13,
    R,
    NC_17;

    public static MpaaRating fromInt(int value) {
        for (MpaaRating mpaaRating : MpaaRating.values()) {
            if (value == mpaaRating.ordinal()) {
                return mpaaRating;
            }
        }
        return null;
    }
}
