package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Offer {

    private Client owner;
    private Collection<Picture> items;

    public Offer(Client owner, Collection<Picture> items){
        this.owner = owner;
        this.items = new LinkedList<>(items);
    }

    public boolean sameAs(Offer offer, Money money){
        return false;
    }

    public int getItemsCount(){

        return items.size();
    }

    public Money getTotalCost(){
        Money totalCost = Money.ZERO;
        for (Picture item : items){
            totalCost = totalCost.add(item.calculatePrice(owner));
        }
        return totalCost;
    }

    public Collection<Picture> getItems() {
        return Collections.unmodifiableCollection(items);   // inny sposób nie pozwalający na modyfikację kolekcji items
    }
}
