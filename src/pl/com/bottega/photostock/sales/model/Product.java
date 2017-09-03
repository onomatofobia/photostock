package pl.com.bottega.photostock.sales.model;

import java.util.Set;

public interface Product {

    Money calculatePrice(Client client);

    boolean isAvailable();

    void reservedPer(Client client);

    void checkReservation(Client client);

    void unreservedPer(Client client);

    void soldPer(Client client);

    Long getNumber();

    default void ensureAvailable(){
        if (!isAvailable())
            throw new ProductNotAvailableException(this);
    }
}
