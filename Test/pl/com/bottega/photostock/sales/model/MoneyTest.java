package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

    private Money fiftyCredit = Money.valueOf(50);
    private Money seventyCredit = Money.valueOf(70);
    private Money fiftyEuro = Money.valueOf(50, "EUR");

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
        //when
        fiftyEuro.add(seventyCredit);
    }

    @Test
    public void shouldSubstractMoney(){
        //when
        Money dif = fiftyCredit.substract(seventyCredit);

        //then
        Money expected = Money.valueOf(-20);
        assertEquals(expected, dif);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSubstractMoneyInDifferentCurrencies(){
        //when
        fiftyEuro.substract(seventyCredit);
    }

    @Test
    public void shouldCompareMoney(){

        assertTrue(fiftyCredit.compareTo(seventyCredit) < 0);
        assertTrue(seventyCredit.compareTo(fiftyCredit) > 0);
        assertTrue(fiftyCredit.compareTo(fiftyCredit) == 0);
    }

    @Test
    public void shouldCompareMoneyUsingBooleanMethods(){

        assertTrue(fiftyCredit.lt(seventyCredit));
        assertTrue(fiftyCredit.lte(seventyCredit));
        assertTrue(seventyCredit.gt(fiftyCredit));
        assertTrue(seventyCredit.gte(fiftyCredit));
        assertFalse(fiftyCredit.gt(seventyCredit));
        assertFalse(fiftyCredit.gte(seventyCredit));
        assertFalse(seventyCredit.lt(fiftyCredit));
        assertFalse(seventyCredit.lte(fiftyCredit));
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
        assertEquals(Money.valueOf(0.02), Money.valueOf(0.19).percent(10));
    }


}
