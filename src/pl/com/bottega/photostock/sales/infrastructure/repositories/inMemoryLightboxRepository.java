package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.LightboxRepository;

import java.util.*;

public class inMemoryLightboxRepository implements LightboxRepository {

    private static final Map<String, LightBox> REPO = new HashMap<>();
    private static Client p1 = new VIPClient("Jan Nowak", new Address("ul. Sielska 11", "Polska", "Lublin", "20-001"));

    static  {

        LightBox lb1 = new LightBox(p1, "LightBox1");
        REPO.put(lb1.getName(), lb1);
    }

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

    @Override
    public List<LightBox> getClientLightBoxes(String clientNumber) {
        List<LightBox> lboxes = new LinkedList<>();
        for(LightBox lightBox : REPO.values())
            if (lightBox.getOwner().getClientNumber().equals(clientNumber))
                lboxes.add(lightBox);
        return lboxes;
    }
}
