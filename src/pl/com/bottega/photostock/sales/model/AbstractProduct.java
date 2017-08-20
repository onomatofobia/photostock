package pl.com.bottega.photostock.sales.model;

import java.util.Set;

public class AbstractProduct implements Product {

    private Long number;
    private Set<String> tags;
    private Money price;
    private Boolean active;
    private Client reservedby;
    private Client owner;


}
