package pl.com.bottega.photostock.sales.model;

import java.util.HashSet;
import java.util.Set;

public class Clip {

    // Dodatkowo ma pole lenght i metodę getLenght();

    public int lenght;
    private Long number;
    private Set<String> tags;
    private Money price;
    private Boolean active;
    private Client reservedby;
    private Client owner;

    public Clip(Long number, Money price, Boolean active, int lenght) {
        this.number = number;
        this.price = price;
        this.active = active;
        this.lenght = lenght;
    }

    public int getLenght() {
        return lenght;
    }
}
