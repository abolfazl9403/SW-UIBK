package org.example.services;
import java.io.IOException;
import java.util.List;
public class GoldConvertService {
    private CurrencyExchangeService exchangeService;

    public GoldConvertService(CurrencyExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    public void convertGoldPrices(List<GoldPrice> goldPrices) throws IOException, InterruptedException {
        if (goldPrices == null || goldPrices.isEmpty()) {
            System.out.println("No gold prices to convert.");
            return;
        }

        double exchangeRate = exchangeService.getExchangeRate();

        for (GoldPrice goldPrice : goldPrices) {
            double convertedPrice = goldPrice.getPrice() * exchangeRate;
            goldPrice.setPrice(convertedPrice);
        }

        double maxPrice = Double.MIN_VALUE;
        double minPrice = Double.MAX_VALUE;
        double totalPrices = 0.0;

        for (GoldPrice goldPrice : goldPrices) {
            double price = goldPrice.getPrice();
            maxPrice = Math.max(maxPrice, price);
            minPrice = Math.min(minPrice, price);
            totalPrices += price;
        }

        double averagePrice = totalPrices / goldPrices.size();

        System.out.println("Maximum Gold Price: " + maxPrice);
        System.out.println("Minimum Gold Price: " + minPrice);
        System.out.println("Average Gold Price: " + averagePrice);
    }
}
