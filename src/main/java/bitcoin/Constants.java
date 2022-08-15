package bitcoin;

/**
 * Contains constant values.
 */
public final class Constants {
    public static final String COIN_DESK_CURRENT_RATE_URL =
            "https://api.coindesk.com/v1/bpi/currentprice/%s.json";
    public static final String COIN_DESK_HISTORICAL_RATE_URL =
            "https://api.coindesk.com/v1/bpi/historical/close" +
            ".json?start=%s&end=%s&currency=%s";

    public static final String INVALID_CURRENCY_SIGNAL = "not supported";

    public static final int HISTORICAL_DAY_COUNT = 120;

    private Constants() {
    }
}
