package bitcoin;

import bitcoin.clients.BitCoinRateClient;
import bitcoin.exceptions.BitcoinRateFetchException;
import com.google.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Supports Bitcoin rate processing against a currency.
 */
public final class BitCoinRateFetcher {

    private final BitCoinRateClient bitCoinRateClient;

    @Inject
    public BitCoinRateFetcher(BitCoinRateClient bitCoinRateClient) {
        this.bitCoinRateClient = bitCoinRateClient;
    }

    /**
     *
     * @param bitCoinHistoricalRates Gets the minimum Bitcoin rate in the currency supplied.
     * @return Minimum Bitcoin rate for the currency.
     */
    Optional<Double> getBitCoinMinRate(List<Double> bitCoinHistoricalRates) {
        if (!bitCoinHistoricalRates.isEmpty()) {
            PriorityQueue<Double> pq = new PriorityQueue<>();
            pq.addAll(bitCoinHistoricalRates);
            return Optional.of(pq.poll());
        }
        return Optional.ofNullable(null);
    }


    /**
     *
     * @param bitCoinHistoricalRates Gets the maximum Bitcoin rate in the currency supplied.
     * @return Maximum Bitcoin rate for the currency.
     */
    Optional<Double> getBitCoinMaxRate(List<Double> bitCoinHistoricalRates) {
        if (!bitCoinHistoricalRates.isEmpty()) {
            PriorityQueue<Double> pq =
                    new PriorityQueue<>(Collections.reverseOrder());
            pq.addAll(bitCoinHistoricalRates);
            return Optional.of(pq.poll());
        }
        return Optional.ofNullable(null);
    }

    /**
     *
     * @param currencyCode Gets the current Bitcoin rate in the currency supplied.
     * @return
     * @throws BitcoinRateFetchException
     */
    Optional<Double> getBitCoinCurrentRate(String currencyCode) throws BitcoinRateFetchException {

        try {
            return bitCoinRateClient.getCurrentRate(currencyCode);
        } catch (BitcoinRateFetchException e) {
            throw new BitcoinRateFetchException(e.getMessage());
        }
    }

    /**
     *
     * @param currencyCode Bitcoin rate for the currency.
     * @return String representing current, minimum and maximum Bitcoin rate.
     * @throws BitcoinRateFetchException
     */
    String displayBitCoinRates(String currencyCode) throws BitcoinRateFetchException {
        String currentRateInfo;
        String minRateInfo;
        String maxRateInfo;

        Optional<Double> currentRate = getBitCoinCurrentRate(currencyCode);

        List<Double> bitCoinHistoricalRates =
                getBitcoinHistoricalRate(currencyCode);

        currentRateInfo = currentRate.isPresent() ?
                String.valueOf(currentRate.get()) :
                "Current Rate unavailable";
        minRateInfo = getBitCoinMinRate(bitCoinHistoricalRates).isPresent() ?
                String.valueOf(getBitCoinMinRate(bitCoinHistoricalRates).get()) :
                " Minimum rate unavailable";
        maxRateInfo = getBitCoinMaxRate(bitCoinHistoricalRates).isPresent() ?
                String.valueOf(getBitCoinMaxRate(bitCoinHistoricalRates).get()) :
                "Maximum rate unavailable";

        return String.format("Current rate: %s\n Minimum rate in the last %s days" +
                        " " +
                        ": %s \n Maximum rate in the last %s days : %s",
                currentRateInfo,Constants.HISTORICAL_DAY_COUNT,
                minRateInfo, Constants.HISTORICAL_DAY_COUNT,maxRateInfo);
    }

    private List<Double> getBitcoinHistoricalRate(String currencyCode) throws BitcoinRateFetchException {
        List<Double> bitCoinHistoricalRates;
        try {
            bitCoinHistoricalRates =
                    bitCoinRateClient.getHistoricalRates(currencyCode,
                            Constants.HISTORICAL_DAY_COUNT);
        } catch (BitcoinRateFetchException e) {
            throw new BitcoinRateFetchException(e.getMessage());
        }
        return bitCoinHistoricalRates;
    }
}
