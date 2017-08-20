package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class LightBox {

    private String name;
    private Client owner;
    private List<Picture> items = new LinkedList<>();

    public LightBox(Client owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public void add(Picture picture) {
        if (items.contains(picture))
            throw new IllegalStateException("Product already added");
        if (!picture.isAvailable())
            throw new IllegalStateException("Product is not available.");
        items.add(picture);
        }

    private void remove(Picture picture) {
        if (!items.remove(picture)) {
            throw new IllegalArgumentException("Product is not part of this LightBox.");
        }
    }

    public String getName() {
        return name;
    }

    public List<Picture> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Client getOwner() {
        return owner;
    }
}
