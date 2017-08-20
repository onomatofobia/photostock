package pl.com.bottega.photostock.sales.model;

public interface Product {

    public Money calculatePrice(Client client);

    public boolean isAvailable();

    public void reservedPer(Client client);

    public void unreservedPer(Client client);

    public Long getNumber();

}
