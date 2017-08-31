package pl.com.bottega.photostock.sales.model;


public class Clip extends AbstractProduct {

    private int lenght;

    public Clip(Long number, Money price, Boolean active, int lenght) {
        super(number, price, active);
        this.lenght = lenght;
    }

    public int getLenght() {
        return lenght;
    }
}
