package pl.com.bottega.photostock.sales.model.repositories;

import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LightboxRepository {

    LightBox get(String number);

    void save(LightBox lightBox);

    List<LightBox> getClientLightBoxes(String clientNumber);
}
