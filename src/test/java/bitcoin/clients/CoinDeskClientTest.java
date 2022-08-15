package bitcoin.clients;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class CoinDeskClientTest {

    @Test
    void shouldFetchCurrentRate_success() throws Exception {
        String testResponse = "{\"time\":{\"updated\":\"Aug 14, 2022 07:14:00" +
                " UTC\",\"updatedISO\":\"2022-08-14T07:14:00+00:00\"," +
                "\"updateduk\":\"Aug 14, 2022 at 08:14 BST\"}," +
                "\"disclaimer\":\"This data was produced from the CoinDesk " +
                "Bitcoin Price Index (USD). Non-USD currency data converted " +
                "using hourly conversion rate from openexchangerates.org\"," +
                "\"bpi\":{\"USD\":{\"code\":\"USD\",\"rate\":\"24,769.8343\"," +
                "\"description\":\"United States Dollar\"," +
                "\"rate_float\":24769.8343},\"EUR\":{\"code\":\"EUR\"," +
                "\"rate\":\"24,129.4350\",\"description\":\"Euro\"," +
                "\"rate_float\":24129.435}}}";
        String url = "https://api.coindesk.com/v1/bpi/currentprice/eur.json";
        HttpService httpServiceMock = Mockito.mock(HttpService.class);
        Mockito.when(httpServiceMock.fetchData(anyString())).thenReturn(testResponse);

        Optional<Double> currentBitCoinRate =
                new CoinDeskClient(httpServiceMock).getCurrentRate("eur");

        assertEquals(24129.435, currentBitCoinRate.get());
        Mockito.verify(httpServiceMock, Mockito.atMostOnce()).fetchData(url);

    }

    @Test
    void shouldFetchHistoricalRate_success() throws Exception {
        String testHistoricalRateResponse = "{\"bpi\":{\"2013-09-01\":97.0904," +
                "\"2013-09-02\":96.4906,\"2013-09-03\":96.8493," +
                "\"2013-09-04\":91.3508,\"2013-09-05\":91.8866}," +
                "\"disclaimer\":\"This data was produced from the CoinDesk " +
                "Bitcoin Price Index. BPI value data returned as EUR.\"," +
                "\"time\":{\"updated\":\"Sep 6, 2013 00:03:00 UTC\"," +
                "\"updatedISO\":\"2013-09-06T00:03:00+00:00\"}}";
        String url = "https://api.coindesk.com/v1/bpi/historical/close" +
                ".json?start=2013-09-01&end=2013-09-05&currency=eur";
        HttpService httpServiceMock = Mockito.mock(HttpService.class);
        Mockito.when(httpServiceMock.fetchData(anyString())).thenReturn(testHistoricalRateResponse);

        List<Double> historicalRates =
                new CoinDeskClient(httpServiceMock).getHistoricalRates("eur",
                        30);

        assertEquals(List.of(97.0904, 96.4906, 96.8493, 91.3508, 91.8866),
                historicalRates);
        Mockito.verify(httpServiceMock, Mockito.atMostOnce()).fetchData(url);
    }

    @Test
    void shouldBuildHistoricalRateUrl_success() throws Exception {
        String fromDate = "2022-07-15";
        String toDate = "2022-08-14";

        String url =
                CoinDeskClient.getHistoricalBitCoinRateEndPointUrl(fromDate,
                        toDate, "euro");

        assertEquals("https://api.coindesk.com/v1/bpi/historical/close" +
                ".json?start=2022-07-15&end=2022-08-14&currency=euro", url);
    }

    @Test
    void shouldBuildCurrentRateUrl_success() throws Exception {

        String url = CoinDeskClient.getCurrentBitCoinRateEndPointUrl("inr");

        assertEquals("https://api.coindesk.com/v1/bpi/currentprice/inr.json",
                url);
    }

    @Test
    void shouldMakeCoinDeskApiRequest_success() throws Exception {

        String url = CoinDeskClient.getCurrentBitCoinRateEndPointUrl("inr");

        assertEquals("https://api.coindesk.com/v1/bpi/currentprice/inr.json", url);
    }
}
