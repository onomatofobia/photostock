package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.*;

import java.util.*;

public class inMemoryReservationRepository implements ReservationRepository {

    private static final Map<String, Reservation> REPO = new HashMap<>();

    @Override
    public Reservation get(String number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No reservation found.");
        return REPO.get(number);
    }

    @Override
    public void save(Reservation reservation) {
            REPO.put(reservation.getNumber(), reservation);
        }

    }
