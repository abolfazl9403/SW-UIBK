package org.example.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoldApiService {
    private static final String GOLD_API_URL = "http://api.nbp.pl/api/cenyzlota";

    public List<GoldPrice> getGoldPrices(String startDate, String endDate) throws IOException, InterruptedException {
        String apiUrl = GOLD_API_URL + "/" + startDate + "/" + endDate;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(response.body());

        List<GoldPrice> goldPrices = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonEntry = jsonArray.getJSONObject(i);

            LocalDate date = LocalDate.parse(jsonEntry.getString("data")); // Replace with the actual field names
            double price = jsonEntry.getDouble("cena"); // Replace with the actual field names

            GoldPrice goldPrice = new GoldPrice(date, price);
            goldPrices.add(goldPrice);
            System.out.println(goldPrices);
        }

        return goldPrices;
    }
}
