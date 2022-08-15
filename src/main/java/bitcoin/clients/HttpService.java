package bitcoin.clients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Defines HTTP service to make HTTP calls and fetch data.
 */
public class HttpService {
    String fetchData(String url) throws URISyntaxException, IOException
            , InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request =
                HttpRequest.newBuilder(new URI(url)).build();
        return httpClient.send(request,
                HttpResponse.BodyHandlers.ofString()).body();
    }
}
