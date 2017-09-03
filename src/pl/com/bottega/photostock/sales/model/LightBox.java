package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class LightBox {

    private String name;
    private Client owner;
    private String number;
    private List<Picture> items = new LinkedList<>();

    public LightBox(Client owner, String name) {
        this.number = UUID.randomUUID().toString();
        this.owner = owner;
        this.name = name;
    }

    public void add(Picture picture) {
        if (items.contains(picture))
            throw new IllegalStateException("Product already added");
        picture.ensureAvailable();
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

    public String getNumber() {
        return number;
    }

    public List<Picture> getPictures(Set<Long> pictureNumbers){

        List<Picture> results = new LinkedList<>();

        for(Picture pic : items) {
            if (pictureNumbers.contains(pic.getNumber()))
                results.add(pic);
        }
        return results;
    }
}
