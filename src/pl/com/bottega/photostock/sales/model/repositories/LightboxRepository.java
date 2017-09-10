package pl.com.bottega.photostock.sales.model.repositories;

import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.Map;
import java.util.Optional;

public interface LightboxRepository {

    LightBox get(String number);

    void save(LightBox lightBox);

    public Map<String, LightBox> getREPO();
}
