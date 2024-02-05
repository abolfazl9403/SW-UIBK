package org.example.services;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class CurrencyExchangeService {
    private static final String EXCHANGE_API_URL = "http://api.nbp.pl/api/exchangerates/rates/c/eur/";

    public double getExchangeRate() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(EXCHANGE_API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());

        double exchangeRate = jsonResponse.getJSONArray("rates")
                .getJSONObject(0)
                .getDouble("bid"); // Replace with the actual field names
        System.out.println("exchange: "+exchangeRate);
        return exchangeRate;
    }
}
