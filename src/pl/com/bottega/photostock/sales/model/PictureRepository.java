package pl.com.bottega.photostock.sales.model;

import java.util.Optional;

public interface PictureRepository {

    //pobiera obiekt po identyfikatorze
    Picture get(Long number);

    Optional<Picture> getOptional(Long number);

    //zapis nowego lub aktualizacja istniejącego obiektu
    void save(Picture picture);
}
