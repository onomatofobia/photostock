package pl.com.bottega.photostock.sales.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClientTest {

    private Address address = new Address("ul. Północna 11", "Polska", "Lublin", "20-001");
    private Client clientWithCredit = new VIPClient("Jan Nowak",
            address,
            ClientStatus.VIP,
            Money.valueOf(100),
            Money.valueOf(100));

    private Client clientWithNoMoney = new VIPClient("Jan Nowak", address);


    @Test
    public void shouldCheckIfClientCanAfford(){

        //when
        clientWithNoMoney.recharge(Money.valueOf(100));

        //then
        assertTrue(clientWithNoMoney.canAfford(Money.valueOf(100)));
        assertTrue(clientWithNoMoney.canAfford(Money.valueOf(50)));
        assertFalse(clientWithNoMoney.canAfford(Money.valueOf(101)));
    }

    @Test
    public void shouldCheckIfClientCanAffordWithCredit(){

        assertTrue(clientWithCredit.canAfford(Money.valueOf(200)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(201)));

    }

    @Test
    public void shouldChargeAndRechargeClient() {

        //when
        clientWithCredit.charge(Money.valueOf(200), "Charge");
        clientWithCredit.recharge(Money.valueOf(100));

        //then
        assertTrue(clientWithCredit.canAfford(Money.valueOf(100)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(101)));
    }

}
