package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.*;

import java.util.*;

public class inMemoryClientRepository implements ClientRepository {

    private static final Map<String, Client> REPO = new HashMap<>();

    static  {
        Client p1 = new VIPClient("Jan Nowak", new Address("ul. Sielska 11", "Polska", "Lublin", "20-001"));
        Client p2 = new VIPClient("Tomasz Kowalski", new Address("ul. Wiosenna 11", "Polska", "Lublin", "20-001"));
        Client p3 = new VIPClient("Janina Wiśniewska", new Address("ul. Północna 11", "Polska", "Lublin", "20-001"));
        REPO.put(p1.getClientNumber(), p1);
        REPO.put(p2.getClientNumber(), p2);
        REPO.put(p3.getClientNumber(), p3);
    }

    @Override
    public Client get(String number){
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No such object in repository");
        return REPO.get(number);
    }

    @Override
    public void save(Client client) {
        REPO.put(client.getClientNumber(), client);
    }


}
