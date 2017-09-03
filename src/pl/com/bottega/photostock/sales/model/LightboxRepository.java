package pl.com.bottega.photostock.sales.model;

import java.util.Optional;

public interface LightboxRepository {

    LightBox get(String number);

    void save(LightBox lightBox);
}
