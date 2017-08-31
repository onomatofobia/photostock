package pl.com.bottega.photostock.sales.ui;
import pl.com.bottega.photostock.sales.infrastructure.*;
import pl.com.bottega.photostock.sales.model.*;

import java.util.HashSet;
import java.util.Set;

public class ConsoleApp {


    public static void main(String[] args) {

        PictureRepository repository = new inMemoryPictureRepository();
        Product p1 = repository.get(1L);
        Product p2 = repository.get(2L);
        Product p3 = repository.get(3L);

        Client client = new Client("Jan Nowak", new Address("ul. Północna 11", "Polska", "Lublin", "20-001"));
        client.recharge(Money.valueOf(1000));
        Reservation reservation = new Reservation(client);

        LightBox lightbox = new LightBox(client, "kotki");
        lightbox.add(p1);
        lightbox.add(p2);
        lightbox.add(p3);

        LightBoxPresenter presenter = new LightBoxPresenter();
        presenter.show(lightbox);

        reservation.add(p1);
        reservation.add(p2);
        reservation.add(p3);

        System.out.println(String.format("W rezerwacji jest %d produktów", reservation.genItemsCount()));

        Offer offer = reservation.generateOffer();
        Money cost = offer.getTotalCost();

        if (client.canAfford(cost)) {
            Purchase purchase = new Purchase(client, offer.getItems());
            client.charge(cost, String.format("Zakup %s", purchase));
            System.out.println(String.format("Ilość zakupionych zjdęć: %d, całkowity koszt: %s",
                    offer.getItemsCount(), offer.getTotalCost()));
        }
        else
            System.out.println("Za mało środków na koncie!");
    }
}
