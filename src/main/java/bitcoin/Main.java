package bitcoin;

import bitcoin.exceptions.BitcoinRateFetchException;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Scanner;

/**
 * Runner for the application.
 */
public final class Main {
    public static void main(String[] args) {
        boolean exitApplicationFlag;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Bitcoin Rate Application.");
        do {
            System.out.println("Please enter the currency code....");
            String currencyCode = scanner.nextLine();
            Injector injector = Guice.createInjector(new BitCoinRateFetcherModule());
            BitCoinRateFetcher fetcher =
                    injector.getInstance(BitCoinRateFetcher.class);
            try {
                System.out.println(String.format("Bitcoin rate information " +
                                "for '%s' currency:\n %s", currencyCode,
                        fetcher.displayBitCoinRates(currencyCode)));
            } catch (BitcoinRateFetchException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Do you want to continue? (y/n)");
            if (scanner.nextLine().equalsIgnoreCase("y"))
                exitApplicationFlag = false;
            else
                exitApplicationFlag = true;

        } while (!exitApplicationFlag);
        System.out.println("Bitcoin Rate Application closed");
    }
}