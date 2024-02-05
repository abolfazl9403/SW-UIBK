import org.example.services.*;

import java.io.IOException;
import java.util.List;

public class SWP1 {
    public static void main(String[] args) {
        try {

            GoldApiService goldApiService = new GoldApiService();
            CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
            GoldConvertService goldConvertService = new GoldConvertService(currencyExchangeService);
            GoldPlotService goldPlotService = new GoldPlotService();


            List<GoldPrice> goldPrices = goldApiService.getGoldPrices("2023-01-01", "2023-12-31");
            goldConvertService.convertGoldPrices(goldPrices);
            goldPlotService.plotGoldPrices(goldPrices);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
    }

}
