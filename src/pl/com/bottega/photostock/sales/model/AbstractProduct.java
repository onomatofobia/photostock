package pl.com.bottega.photostock.sales.model;

import java.util.Set;

public abstract class AbstractProduct implements Product {

    private Long number;
    private Set<String> tags;
    private Money price;
    private Boolean active;
    private Client reservedby;
    private Client owner;

    public AbstractProduct(Long number, Money price, Boolean active) {
        this.number = number;
        this.price = price;
        this.active = active;
    }

    @Override
    public Money calculatePrice(Client client){
        return price.percent(100 - client.discountPercent());
    }

    @Override
    public boolean isAvailable(){
        return active && reservedby == null;
    }

    @Override
    public void reservedPer(Client client){
        if (!isAvailable())
            throw new IllegalStateException("Product is not available.");
        reservedby = client;
    }

    @Override
    public void checkReservation(Client client){
        if (reservedby == null || !reservedby.equals(client))
            throw new IllegalStateException(String.format("Product is not reserved by %s", client));
    }

    @Override
    public void unreservedPer(Client client){
        if (owner != null)
            throw new IllegalStateException("Product is already purchased");
        checkReservation(client);
        reservedby = null;
    }

    @Override
    public void soldPer(Client client) {
        checkReservation(client);
        owner = client;
    }

    @Override
    public Long getNumber(){
        return number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractProduct that = (AbstractProduct) o;

        if (!number.equals(that.number)) return false;
        if (!tags.equals(that.tags)) return false;
        if (!price.equals(that.price)) return false;
        if (!active.equals(that.active)) return false;
        if (!reservedby.equals(that.reservedby)) return false;
        return owner.equals(that.owner);
    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + tags.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + active.hashCode();
        result = 31 * result + reservedby.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AbstractProduct{" +
                "number=" + number +
                ", tags=" + tags +
                ", price=" + price +
                ", active=" + active +
                ", reservedby=" + reservedby +
                ", owner=" + owner +
                '}';
    }
}
