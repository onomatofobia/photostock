package pl.com.bottega.photostock.sales.model;

import java.text.CollationElementIterator;
import java.time.LocalDateTime;
import java.util.*;

public class Purchase {

    private Collection<Product> items;
    private Client buyer;
    private LocalDateTime purchaseDate = LocalDateTime.now();

    public Purchase(Client buyer, Collection<Product> items){
        this.buyer = buyer;
        this.items = new LinkedList<>(items);
    }
}
