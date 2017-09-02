package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class CurrencyConverter {

    private String mainCurrency;
    private Map<String, Double> exchangeRates;

    public CurrencyConverter(String mainCurrency, Map<String, Double> exchangeRates){

        this.mainCurrency = mainCurrency;
        this.exchangeRates = exchangeRates;
    }

/*    Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 3.6020);
        rates.put("EUR", 4.2345);
    CurrencyConverter c = new CurrencyConverter("PLN", rates);*/


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
            return convert(convert(amount), currency);

    }

    public Double getExchangeRates(String currency) {
        if (!exchangeRates.containsKey(currency))
            throw new IllegalArgumentException("There is no exchange rate for " + currency);
        return exchangeRates.get(currency);
    }
}
