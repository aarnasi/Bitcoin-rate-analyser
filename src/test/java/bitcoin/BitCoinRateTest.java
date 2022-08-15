package bitcoin;

import bitcoin.clients.CoinDeskClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitCoinRateTest {
    static final double MIN_RATE_EURO = 1000000.80;
    static final double AVG_RATE_EURO = 1500000.80;
    static final double MAX_RATE_EURO = 2000000.80;

    @Test
    void shouldFetchBitCoinMaxValue() throws Exception {

        CoinDeskClient coinDeskClient = Mockito.mock(CoinDeskClient.class);

        Optional<Double> maxBitCoinRate =
                new BitCoinRateFetcher(coinDeskClient).getBitCoinMaxRate(List.of(MIN_RATE_EURO, MAX_RATE_EURO, AVG_RATE_EURO));

        assertEquals(MAX_RATE_EURO, maxBitCoinRate.get());
    }

    @Test
    void shouldFetchBitCoinMinValue() throws Exception {
        CoinDeskClient coinDeskClient = Mockito.mock(CoinDeskClient.class);

        Optional<Double> minBitCoinRate =
                new BitCoinRateFetcher(coinDeskClient).getBitCoinMinRate(List.of(MIN_RATE_EURO, MAX_RATE_EURO, AVG_RATE_EURO));

        assertEquals(MIN_RATE_EURO, minBitCoinRate.get());
    }
}
