package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class LightBox {

    private String name;
    private Client owner;
    private List<Product> items = new LinkedList<>();

    public LightBox(Client owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public void add(Product product) {
        if (items.contains(product))
            throw new IllegalStateException("Product already added");
        if (!product.isAvailable())
            throw new IllegalStateException("Product is not available.");
        items.add(product);
        }

    private void remove(Product product) {
        if (!items.remove(product)) {
            throw new IllegalArgumentException("Product is not part of this LightBox.");
        }
    }

    public String getName() {
        return name;
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Client getOwner() {
        return owner;
    }
}
