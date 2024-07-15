package com.example.currencyconverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter currencyConverter = new CurrencyConverter();

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = scanner.nextDouble();

        System.out.print("Ingrese la moneda de origen (por ejemplo, USD): ");
        String fromCurrency = scanner.next().toUpperCase();

        System.out.print("Ingrese la moneda de destino (por ejemplo, EUR): ");
        String toCurrency = scanner.next().toUpperCase();

        try {
            double convertedAmount = currencyConverter.convert(amount, fromCurrency, toCurrency);
            System.out.println(amount + " " + fromCurrency + " = " + convertedAmount + " " + toCurrency);
        } catch (Exception e) {
            System.err.println("Error al obtener la tasa de cambio: " + e.getMessage());
        }

        scanner.close();
    }
}
