package com.example.currencyconverter;

public class CurrencyConverter {

    private ExchangeRateService exchangeRateService;

    public CurrencyConverter() {
        this.exchangeRateService = new ExchangeRateService();
    }

    public double convert(double amount, String fromCurrency, String toCurrency) throws Exception {
        double exchangeRate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        return amount * exchangeRate;
    }
}
