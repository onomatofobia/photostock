package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class CurrencyConverter {

    private String mainCurrency;
    private Map<String, Double> exchangeRates;

    public CurrencyConverter(String mainCurrency, Map<String, Double> exchangeRates){

        this.mainCurrency = mainCurrency;
        this.exchangeRates = exchangeRates;
    }

    public Money convert(Money amount){
        CurrencyConverter c = new CurrencyConverter(mainCurrency, exchangeRates);
        if (amount.currency().equals(mainCurrency))
            return amount;
        else
            return amount.convert(mainCurrency, getExchangeRates(amount.currency()));
    }

    public Money convert(Money amount, String currency){
        if (currency.equals(mainCurrency))
            return convert(amount);
        else if (amount.currency().equals(mainCurrency))
            return amount.convert(currency, 1/getExchangeRates(currency));
        else
            return amount.convert(currency, getExchangeRates(amount.currency()) / getExchangeRates(currency));

    }

    public Double getExchangeRates(String currency) {
        if (!exchangeRates.containsKey(currency))
            throw new IllegalArgumentException("There is no exchange rate for " + currency);
        return exchangeRates.get(currency);
    }
}
