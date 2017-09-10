package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.PurchaseRepository;

import java.util.HashMap;
import java.util.Map;

public class inMemoryPurchaseRepository implements PurchaseRepository {

    private static final Map<String, Purchase> REPO = new HashMap<>();

    @Override
    public void save(Purchase purchase) {
        REPO.put(purchase.getNumber(), purchase);
    }

    @Override
    public Purchase get(String number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No purchase found.");
        return REPO.get(number);
    }
}
