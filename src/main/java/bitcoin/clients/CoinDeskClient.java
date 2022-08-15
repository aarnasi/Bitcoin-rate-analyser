package bitcoin.clients;

import bitcoin.Constants;
import bitcoin.exceptions.BitcoinRateFetchException;
import bitcoin.types.BitCoinCurrentValue;
import bitcoin.types.BitCoinHistoricalValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bitcoin.Constants.COIN_DESK_CURRENT_RATE_URL;
import static bitcoin.Constants.COIN_DESK_HISTORICAL_RATE_URL;

/**
 * Implements BitCoinRateClient to consume Coin desk APIs.
 */
public class CoinDeskClient implements BitCoinRateClient {

    private final HttpService httpService;

    @Inject
    CoinDeskClient(HttpService httpService) {
        this.httpService = httpService;
    }

    static String getCurrentBitCoinRateEndPointUrl(String currency) {
        return String.format(COIN_DESK_CURRENT_RATE_URL, currency);
    }

    static String getHistoricalBitCoinRateEndPointUrl(String fromDate,
                                                      String toDate,
                                                      String currency) {
        return String.format(COIN_DESK_HISTORICAL_RATE_URL, fromDate, toDate,
                currency);
    }

    @Override
    public List<Double> getHistoricalRates(String currency, int days) throws BitcoinRateFetchException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate toDate = LocalDate.parse(LocalDate.now().format(formatter),
                formatter);
        LocalDate fromDate = LocalDate.parse(LocalDate.now().minus(days,
                ChronoUnit.DAYS).format(formatter), formatter);

        String response;
        BitCoinHistoricalValue history;

        try {
            response =
                    httpService.fetchData(getHistoricalBitCoinRateEndPointUrl(fromDate.toString(), toDate.toString(), currency));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new BitcoinRateFetchException(e.getMessage());
        }

        try {
            if (response.contains(Constants.INVALID_CURRENCY_SIGNAL)) {
                throw new BitcoinRateFetchException(String.format("Invalid " +
                        "currency: %s", currency));
            }
            history = new ObjectMapper().readValue(response,
                    BitCoinHistoricalValue.class);
        } catch (IOException e) {
            throw new BitcoinRateFetchException(String.format("Invalid " +
                    "response from source: %s", e.getMessage()));
        }
        if (history.getBpi() != null && !history.getBpi().isEmpty()) {
            return history.getBpi().values().stream().collect(Collectors.toUnmodifiableList());
        }
        throw new BitcoinRateFetchException(String.format("Historical Bitcoin" +
                " rate for %s currency is unavailable", currency));
    }

    @Override
    public Optional<Double> getCurrentRate(String currency) throws BitcoinRateFetchException {
        BitCoinCurrentValue currentValue;
        String response;
        try {
            response =
                    httpService.fetchData(getCurrentBitCoinRateEndPointUrl(currency));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new BitcoinRateFetchException(e.getMessage());
        }
        try {
            if (response.contains(Constants.INVALID_CURRENCY_SIGNAL)) {
                throw new BitcoinRateFetchException(String.format("Invalid " +
                        "currency: %s", currency));
            }
            currentValue = new ObjectMapper().readValue(response,
                    BitCoinCurrentValue.class);
        } catch (IOException e) {
            throw new BitcoinRateFetchException(String.format("Invalid " +
                    "response from source: %s", e.getMessage()));
        }
        if (currentValue.getBpi() != null && !currentValue.getBpi().isEmpty()) {
            return Optional.of(Double.valueOf(currentValue.getBpi().get(currency.toUpperCase()).get("rate_float")));
        }
        throw new BitcoinRateFetchException(String.format("Current Bitcoin " +
                "rate for %s currency is unavailable", currency));
    }
}
