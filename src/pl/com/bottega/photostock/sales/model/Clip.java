package pl.com.bottega.photostock.sales.model;


public class Clip extends AbstractProduct {

    private Long length;

    public Clip(Long number, Money price, Boolean active, Long length) {
        super(number, price, active);
        this.length = length;
    }

    public Clip(Money price, Long number, Long length) {
        this(number, price, true, length);
    }
}
