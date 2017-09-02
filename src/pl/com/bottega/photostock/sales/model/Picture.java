package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Picture extends AbstractProduct {

    private Set<String> tags;



    public Picture(Long number, Money price, Boolean active, Set<String> tags) {
        super(number, price, active);
        this.tags = new HashSet<>(tags);
    }

    public Picture(Long number, Money price, Set<String> tags){

        this(number, price, true, tags);

    }

    public Set<String> getTags() {
        return tags;
    }

}
