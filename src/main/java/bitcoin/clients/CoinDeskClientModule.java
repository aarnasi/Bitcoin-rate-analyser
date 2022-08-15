package bitcoin.clients;

import com.google.inject.AbstractModule;

/**
 * Guice module for CoinDeskClient.
 */
public class CoinDeskClientModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(HttpService.class).toInstance(new HttpService());
        bind(BitCoinRateClient.class).to(CoinDeskClient.class);
    }
}
