package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.repositories.LightboxRepository;

import java.util.HashMap;
import java.util.Map;

public class inMemoryLightboxRepository implements LightboxRepository {

    private static final Map<String, LightBox> REPO = new HashMap<>();

    @Override
    public LightBox get(String number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No such object in repository");
        return REPO.get(number);
    }

    @Override
    public void save(LightBox lightBox) {
        REPO.put(lightBox.getNumber(), lightBox);
    }

    public  Map<String, LightBox> getREPO() {
        return REPO;
    }
}
