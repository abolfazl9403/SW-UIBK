import org.example.services.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GoldConvertServiceTest {

    @Test
    void testConvertGoldPrices() throws IOException, InterruptedException {

        CurrencyExchangeService mockedExchangeService = Mockito.mock(CurrencyExchangeService.class);
        GoldConvertService goldConvertService = new GoldConvertService(mockedExchangeService);

        when(mockedExchangeService.getExchangeRate()).thenReturn(1.2); // Assuming an exchange rate of 1.2 for testing

        List<GoldPrice> goldPrices = new ArrayList<>();
        goldPrices.add(new GoldPrice(LocalDate.parse("2023-01-02"), 256.67));
        goldPrices.add(new GoldPrice(LocalDate.parse("2023-01-03"), 255.46));
        goldPrices.add(new GoldPrice(LocalDate.parse("2023-01-04"), 262.95));

        goldConvertService.convertGoldPrices(goldPrices);

        assertEquals(308.004, goldPrices.get(0).getPrice(), 0.0001);
        assertEquals(306.552, goldPrices.get(1).getPrice(), 0.0001);
        assertEquals(315.53999, goldPrices.get(2).getPrice(), 0.0001);

    }
}
