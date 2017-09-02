package pl.com.bottega.photostock.sales.model;

import java.util.*;

public interface ProductRepository {

    //pobiera obiekt po identyfikatorze
    Product get(Long number);

    Optional<Product> getOptional(Long number);

    //zapis nowego lub aktualizacja istniejącego obiektu
    void save(Product product);
}
