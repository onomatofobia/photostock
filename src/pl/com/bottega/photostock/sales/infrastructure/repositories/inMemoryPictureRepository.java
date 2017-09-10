package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.repositories.PictureRepository;

import java.util.*;

public class inMemoryPictureRepository implements PictureRepository{

    private static final Map<Long, Picture> REPO = new HashMap<>();

    static  {
        Set<String> tags = new HashSet<>();
        tags.add("kotki");
        Picture p1 = new Picture(1L, Money.valueOf(10), true, tags);
        Picture p2 = new Picture(2L, Money.valueOf(5), true, tags);
        Picture p3 = new Picture(3L, Money.valueOf(15), true, tags);
        REPO.put(1L, p1);
        REPO.put(2L, p2);
        REPO.put(3L, p3);
    }

    @Override
    public Picture get(Long number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No such object in repository");
        return REPO.get(number);
    }

    @Override
    public Optional<Picture> getOptional(Long number) {
        if (REPO.containsKey(number))
            return Optional.of(REPO.get(number));
        else
            return Optional.empty();
    }

    @Override
    public void save(Picture picture) {
        REPO.put(picture.getNumber(), picture);
    }
}
