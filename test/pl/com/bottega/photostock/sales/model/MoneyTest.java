package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 19.08.2017.
 */

public class MoneyTest {

    private Money fiftyCredit = Money.valueOf(50);
    private Money seventyCredit = Money.valueOf(70);
    private Money fiftyEuro = Money.valueOf(50, "EUR");
    private Money seventyDollars = Money.valueOf(70, "USD");

    @Test
    public void shouldAddMoney(){
        //when
        Money sum = fiftyCredit.add(seventyCredit);
        //then
        Money expected = Money.valueOf(120);
        assertEquals(expected, sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddMoneyInDifferentCurrencies(){
        //given

        //when
        fiftyEuro.add(seventyDollars);
    }

    @Test
    public void shouldSubtractMoney(){
        //when
        Money diff = seventyCredit.sub(fiftyCredit);
        //then
        Money expected = Money.valueOf(20);
        assertEquals(expected, diff);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSubtractMoneyInDifferentCurrencies(){
        //given

        //when
        Money difference = seventyDollars.sub(fiftyEuro);
        //then
        Money expected = Money.valueOf(20);
        assertEquals(expected, difference);
    }
    @Test
    public void shouldCompareMoney() {
        assertTrue(fiftyCredit.compareTo(seventyCredit) < 0);
        assertTrue(seventyCredit.compareTo(fiftyCredit) > 0);
        assertTrue(seventyCredit.compareTo(fiftyCredit) > 0);
    }

    @Test
    public void shouldCompareMoneyUsingBooleanMethods(){
        assertTrue(fiftyCredit.lt(seventyCredit));
        assertTrue(fiftyCredit.lte(seventyCredit));
        assertTrue(seventyCredit.gt(fiftyCredit));
        assertTrue(seventyCredit.gte(fiftyCredit));
        assertFalse(seventyCredit.lt(fiftyCredit));
        assertFalse(seventyCredit.lte(fiftyCredit));
        assertFalse(fiftyCredit.gt(seventyCredit));
        assertFalse(fiftyCredit.gte(seventyCredit));
        assertTrue(fiftyCredit.gte(fiftyCredit));
        assertTrue(fiftyCredit.lte(fiftyCredit));
        assertFalse(fiftyCredit.lt(fiftyCredit));
        assertFalse(fiftyCredit.gt(fiftyCredit));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCompareDifferentCurrencies(){
        fiftyCredit.compareTo(fiftyEuro);
    }

    @Test
    public void shouldCalculatePercent(){
        assertEquals(Money.valueOf(5), fiftyCredit.percent(10));
        assertEquals(Money.valueOf(5.50), fiftyCredit.percent(11));
        assertEquals(Money.valueOf(75), fiftyCredit.percent(150));
        assertEquals(Money.valueOf(0.01), Money.valueOf(0.11).percent(10));
        assertEquals(Money.valueOf(0.01), Money.valueOf(0.19).percent(10));



    }

    @Test
    public void shouldConvertMoney(){
        assertEquals(fiftyEuro, fiftyCredit.convert("EUR", 1d));
        assertEquals(seventyCredit,fiftyEuro.convert(Money.DEFAULT_CURRENCY, 1.4));
    }


}
