package client.data;

public enum Country {
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
