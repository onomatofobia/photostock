package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.*;

import java.util.*;

public class inMemoryProductRepository implements ProductRepository {

    private static final Map<Long, Product> REPO = new HashMap<>();

    static  {
        Set<String> tags = new HashSet<>();
        tags.add("kotki");
        Product p1 = new Picture(1L, Money.valueOf(10), true, tags);
        Product p2 = new Picture(2L, Money.valueOf(5), true, tags);
        Product p3 = new Picture(3L, Money.valueOf(15), true, tags);
        REPO.put(1L, p1);
        REPO.put(2L, p2);
        REPO.put(3L, p3);
    }

    @Override
    public Product get(Long number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No such object in repository");
        return REPO.get(number);
    }

    @Override
    public Optional<Product> getOptional(Long number) {
        if (REPO.containsKey(number))
            return Optional.of(REPO.get(number));
        else
            return Optional.empty();
    }

    @Override
    public void save(Product product) {
        REPO.put(product.getNumber(), product);
    }
}


