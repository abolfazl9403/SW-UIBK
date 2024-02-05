import org.example.services.CurrencyExchangeService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CurrencyExchangeServiceTest {

    private static final String EXCHANGE_API_URL = "http://api.nbp.pl/api/exchangerates/rates/c/eur/";

    @Test
    void testGetExchangeRate() throws IOException, InterruptedException {

        try (MockedStatic<HttpClient> httpClientMock = Mockito.mockStatic(HttpClient.class)) {

            HttpClient mockedClient = Mockito.mock(HttpClient.class);
            HttpResponse<String> mockedResponse = Mockito.mock(HttpResponse.class);

            CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();

            httpClientMock.when(() -> HttpClient.newHttpClient()).thenReturn(mockedClient);
            when(mockedClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                    .thenReturn(mockedResponse);
            when(mockedResponse.body()).thenReturn("{ \"rates\": [{ \"bid\": 1.2345 }] }");

            double exchangeRate = currencyExchangeService.getExchangeRate();
            Mockito.verify(mockedClient).send(Mockito.argThat(request ->
                    request.uri().equals(URI.create(EXCHANGE_API_URL))), Mockito.any(HttpResponse.BodyHandler.class));
            System.out.println(exchangeRate);

            assertEquals(1.2345, exchangeRate, 0.0001);
        }
    }
}
