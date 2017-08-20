package pl.com.bottega.photostock.sales.model;

public class Address {

    private String line1, line2, country, city, potalCode;

    public Address(String line1, String line2, String country, String city, String potalCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.country = country;
        this.city = city;
        this.potalCode = potalCode;
    }

    public Address(String line1, String country, String city, String potalCode) {
        this(line1, null, country, city, potalCode);
    }
}
