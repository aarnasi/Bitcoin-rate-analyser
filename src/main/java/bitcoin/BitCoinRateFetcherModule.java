package bitcoin;

import bitcoin.clients.CoinDeskClientModule;
import com.google.inject.AbstractModule;

/**
 * Guice module for BitCoinRateFetcher.
 */
public final class BitCoinRateFetcherModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new CoinDeskClientModule());
    }
}
