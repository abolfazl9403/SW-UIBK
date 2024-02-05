import org.example.services.GoldApiService;
import org.example.services.GoldPrice;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GoldApiServiceTest {

    private static final String GOLD_API_URL = "http://api.nbp.pl/api/cenyzlota";

    @Test
    void testGetGoldPrices() throws IOException, InterruptedException {
        try (MockedStatic<HttpClient> httpClientMock = Mockito.mockStatic(HttpClient.class)) {
            HttpClient mockedClient = Mockito.mock(HttpClient.class);
            HttpResponse<String> mockedResponse = Mockito.mock(HttpResponse.class);

            GoldApiService goldApiService = new GoldApiService();

            httpClientMock.when(() -> HttpClient.newHttpClient()).thenReturn(mockedClient);
            when(mockedClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                    .thenReturn(mockedResponse);
            when(mockedResponse.body()).thenReturn("[{ \"data\": \"2023-01-02\", \"cena\": 256.67 }, { \"data\": \"2023-01-03\", \"cena\": 255.46 }]");

            List<GoldPrice> goldPrices = goldApiService.getGoldPrices("2023-01-02", "2023-01-03");
            Mockito.verify(mockedClient).send(Mockito.argThat(request ->
                    request.uri().equals(URI.create(GOLD_API_URL + "/2023-01-02/2023-01-03"))), Mockito.any(HttpResponse.BodyHandler.class));


            assertEquals(2, goldPrices.size());

            assertEquals(LocalDate.parse("2023-01-02"), goldPrices.get(0).getDate());
            assertEquals(256.67, goldPrices.get(0).getPrice(), 0.0001);

            assertEquals(LocalDate.parse("2023-01-03"), goldPrices.get(1).getDate());
            assertEquals(255.46, goldPrices.get(1).getPrice(), 0.0001);
        }
    }
}
