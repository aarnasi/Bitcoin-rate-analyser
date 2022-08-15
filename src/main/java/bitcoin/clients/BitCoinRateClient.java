package bitcoin.clients;

import bitcoin.exceptions.BitcoinRateFetchException;

import java.util.List;
import java.util.Optional;

/**
 * Defines supported operations to fetch Bitcoin rates.
 */
public interface BitCoinRateClient {
    /**
     *
     * @param currency The currency for which the Bitcoin rate is needed.
     * @param days     The number of days for which Bitcoin rates need to be fetched.
     * @return The list of rates in floating point.
     * @throws BitcoinRateFetchException
     */
    List<Double> getHistoricalRates(String currency, int days) throws BitcoinRateFetchException;

    /**
     *
     * @param currency The currency for which the Bitcoin rate is needed.
     * @return Current Bitcoin rate in the currency.
     * @throws BitcoinRateFetchException
     */
    Optional<Double> getCurrentRate(String currency) throws BitcoinRateFetchException;
}
