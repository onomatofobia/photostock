package pl.com.bottega.photostock.sales.model;

public interface ReservationRepository {

    Reservation get(String number);

    void save(Reservation reservation);
}
