package pl.com.bottega.photostock.sales.model;

import java.util.*;
import pl.com.bottega.photostock.sales.model.ClientStatus.*;

public class Picture {

    private Long number;
    private Set<String> tags;
    private Money price;
    private Boolean active;
    private Client reservedby;
    private Client owner;

    public Picture(Long number, Set<String> tags, Money price){
        this(number, tags, price, true);
    }

    public Picture(Long number, Set<String> tags, Money price, Boolean active) {
        this.number = number;
        this.tags = new HashSet<>(tags);
        this.price = price;
        this.active = active;
    }

    public Money calculatePrice(Client client){
        return price.percent(100 - client.discountPercent());
        }


    public boolean isAvailable(){
        return active && reservedby == null;
    }

    public void reservedPer(Client client){

        if (!isAvailable())
            throw new IllegalStateException("Product is not available.");
        reservedby = client;
    }

    public void unreservedPer(Client client){
        if (owner != null)
            throw new IllegalStateException("Product is already purchased");
        checkReservation(client);
        reservedby = null;
    }

    private void checkReservation(Client client) {
        if (reservedby == null || !reservedby.equals(client))
            throw new IllegalStateException(String.format("Product is not reserved by %s", client));
    }

    public void soldPer(Client client) {
        checkReservation(client);
        owner = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        return number.equals(picture.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    public Long getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "\nPicture{" +
                "number=" + number +
                ", tags=" + tags +
                ", price=" + price +
                ", active=" + active +
                ", \nreservedby=" + reservedby +
                ", owner=" + owner +
                '}';
    }
}
