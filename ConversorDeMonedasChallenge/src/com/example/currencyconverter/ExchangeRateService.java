package com.example.currencyconverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateService {

    private static final String API_KEY = "558b2d798f08aed62afee401";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY;

    public double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String urlStr = BASE_URL + "/latest/" + fromCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return parseExchangeRate(response.toString(), toCurrency);
    }

    private double parseExchangeRate(String jsonResponse, String toCurrency) throws Exception {
        String searchKey = "\"" + toCurrency + "\":";
        int startIndex = jsonResponse.indexOf(searchKey) + searchKey.length();
        int endIndex = jsonResponse.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonResponse.indexOf("}", startIndex);
        }
        if (startIndex == -1 || endIndex == -1) {
            throw new Exception("Invalid JSON response");
        }
        return Double.parseDouble(jsonResponse.substring(startIndex, endIndex));
    }
}

