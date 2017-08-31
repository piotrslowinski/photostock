package pl.com.bottega.photostock.sales.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 28.08.2017.
 */
public class CurrencyConverter {


    private final String mainCurrency;
    private Map<String, Double> exchangeRates = new HashMap<>();

    public CurrencyConverter(String mainCurrency, Map<String, Double> exchangeRates){
        this.mainCurrency = mainCurrency;
        this.exchangeRates = exchangeRates;
    }

    public Money convert(Money amount){
        String tmpCurrency = amount.currency();
        if (!isProperCurrency(tmpCurrency))
            throw new IllegalArgumentException("Such a currency doesn't exist in exchangr table");
        if(tmpCurrency.equals(mainCurrency))
            return amount;
        return amount.convert(mainCurrency, getExchangeRate(tmpCurrency));
    }

    public Money convert(Money amount, String currency){
        if (!isProperCurrency(currency))
            throw new IllegalArgumentException("Such a currency doesn't exist in exchangr table");
        if (currency.equals(mainCurrency))
            return convert(amount);
        String tmpCurrency = amount.currency();
        if (!isProperCurrency(tmpCurrency))
            throw new IllegalArgumentException("Such a currency doesn't exist in exchangr table");
        if (tmpCurrency.equals(mainCurrency))
            return amount.convert(currency, (double)(1/getExchangeRate(currency)));
        else
            return amount.convert(currency, getExchangeRate(tmpCurrency) / getExchangeRate(currency));
    }

    private Double getExchangeRate(String currency) {
        return exchangeRates.get(currency);
    }

    private boolean isProperCurrency(String currency) {
        return exchangeRates.containsKey(currency) || mainCurrency.equals(currency);
    }
}
