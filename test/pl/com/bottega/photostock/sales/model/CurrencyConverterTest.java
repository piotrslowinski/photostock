package pl.com.bottega.photostock.sales.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 30.08.2017.
 */
public class CurrencyConverterTest {

    private Map<String, Double> exRatesPLN = new HashMap<>();
    private CurrencyConverter converterTest;



    @Before
    public void setUp() throws Exception {
        exRatesPLN.put("USD", 3.6020);
        exRatesPLN.put("EUR", 4.2345);
        converterTest = new CurrencyConverter("PLN", exRatesPLN);
    }

    @Test
    public void shouldConvertToMainCurrency(){
        Money convertedEUR = converterTest.convert(Money.valueOf(50,"EUR"));
        Money convertedUSD = converterTest.convert(Money.valueOf(50, "USD"));

        assertEquals(Money.valueOf(50*4.2345,"PLN"), convertedEUR);
        assertEquals(Money.valueOf(50*3.6020, "PLN"), convertedUSD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalCurrency(){
        Money convertedRUB = converterTest.convert(Money.valueOf(50,"RUB"));
    }

    @Test
    public void shouldConvertToGivenCurrency(){
        Money c1 = converterTest.convert(Money.valueOf(50, "EUR"),"PLN");
        Money c2 = converterTest.convert(Money.valueOf(50, "PLN"), "EUR");
        Money c3 = converterTest.convert(Money.valueOf(10,"PLN"),"PLN");
        Money c4 = converterTest.convert(Money.valueOf(50, "EUR"),"USD");
        assertEquals(Money.valueOf(50*4.2345, "PLN"), c1);
        assertEquals(Money.valueOf(50/4.2345,"EUR"),c2);
        assertEquals(Money.valueOf(10,"PLN"), c3);
        assertEquals(Money.valueOf(50*4.2345/3.6020, "USD"),c4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalCurrencyInTwoParameterMethod(){
        Money c1 = converterTest.convert(Money.valueOf(10,"RUB"),"PLN");
        Money c2 = converterTest.convert(Money.valueOf(10,"RUB"), "DIN");
    }


}
