package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorAPI {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private final String apiKey;

    public ConversorAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obterTaxaCambio(String moedaOrigem, String moedaDestino) throws Exception {
        URL url = new URL(API_URL + moedaOrigem);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
            return jsonResponse
                    .getAsJsonObject("rates")
                    .get(moedaDestino)
                    .getAsDouble();
        }
    }
}