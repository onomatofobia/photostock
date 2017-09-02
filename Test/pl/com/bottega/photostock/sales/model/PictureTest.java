package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PictureTest {

    @Test
    public void shouldCalculatePriceBasingOnClientStatus(){

        Client clientVip = new VIPClient("Jan Nowak",
                new Address("ul. Północna 11", "Polska", "Lublin", "20-001"),
                ClientStatus.VIP,
                Money.ZERO,
                Money.ZERO);

        Client clientStandard = new VIPClient("Jan Nowak",
                new Address("ul. Północna 11", "Polska", "Lublin", "20-001"),
                ClientStatus.STANDARD,
                Money.ZERO,
                Money.ZERO);


    }
}
