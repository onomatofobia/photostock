package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.*;

import java.util.List;
import java.util.Set;

public class LightboxManagement {

    private LightboxRepository lightboxRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private ReservationRepository reservationRepository;

    public LightboxManagement(LightboxRepository lightboxRepository,
                              ClientRepository clientRepository,
                              ProductRepository productRepository,
                              ReservationRepository reservationRepository){
        this.lightboxRepository = lightboxRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
    }


    public String create(String clientNumber, String lightBoxName){

        Client client = clientRepository.get(clientNumber);

        LightBox lightBox = new LightBox(client, lightBoxName);

        lightboxRepository.save(lightBox);

        return lightBox.getNumber();
    }

    public void add(String lightBoxNumber, Long pictureNumber){

        Product product = productRepository.get(pictureNumber);
        if (!(product instanceof Picture)) {
            throw new IllegalArgumentException("This is not a Picture number");
        }
        LightBox lightBox = lightboxRepository.get(lightBoxNumber);
        Picture picture = (Picture) product;
        lightBox.add(picture);
        lightboxRepository.save(lightBox);
        productRepository.save(product);
    }


    public void reserve(String lightBoxNumber, Set<Long> picturesNumbers, String reservationNumber){
        LightBox lightBox = lightboxRepository.get(lightBoxNumber);
        Reservation reservation = reservationRepository.get(reservationNumber);
        List<Picture> pictures = lightBox.getPictures(picturesNumbers);

        if (picturesNumbers.size() != pictures.size())
            throw new IllegalArgumentException("Invalid product numbers");

        for (Picture picture : pictures)
            picture.ensureAvailable();
        for(Picture picture : pictures){
            reservation.add(picture);
            productRepository.save(picture);
        }
        reservationRepository.save(reservation);
    }

    public List<LightBox> getLightBoxes(String clientNumber) {
        Client client = clientRepository.get(clientNumber);
        List<LightBox> lightBoxes = null;
        List<LightBox> lightBoxesFromRepo = (List) lightboxRepository.getREPO().values();

        for (LightBox lightBox : lightBoxesFromRepo) {
            if (lightBox.getOwner().equals(client))
                lightBoxes.add(lightBox);
        }
        return lightBoxes;
    }
}

