package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.application.PurchaseStatus;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.LightboxRepository;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PurchaseLightBoxScreen {

    private LightBoxManagement lightBoxManagement;
    private PurchaseProcess purchaseProcess;
    private Scanner scanner;

    public PurchaseLightBoxScreen(LightBoxManagement lightBoxManagement, PurchaseProcess purchaseProcess, Scanner scanner) {
        this.lightBoxManagement = lightBoxManagement;
        this.purchaseProcess = purchaseProcess;
        this.scanner = scanner;
    }

    public void show(LightBox lightBox){
        Set<Long> numbers = new HashSet<>();
        for (Product p : lightBox.getItems())
            numbers.add(p.getNumber());

        numbers = lightBox.getItems().stream().map(product -> product.getNumber()).collect(Collectors.toSet());

        String reservationNumber = purchaseProcess.createReservation(lightBox.getOwner().getClientNumber());
        lightBoxManagement.reserve(lightBox.getNumber(), numbers, reservationNumber);

        Offer offer = purchaseProcess.calculateOffer(reservationNumber);

        System.out.println(String.format("Cena wybranych zdjęć: %s", offer.getTotalCost()));
        System.out.print("Czy chcesz dokonać zakupu? (t/n) ");
        String decision = scanner.nextLine();
        if(decision.equals("t")){
            PurchaseStatus status = purchaseProcess.confirm(reservationNumber, offer);
            if(status.equals(PurchaseStatus.SUCCESS))
                System.out.println("Dziękujemy za udane zakupy!");
            else if(status.equals(PurchaseStatus.NOT_ENOUGH_FUNDS))
                System.out.println("Przykro mi, ale nie masz wystarczających środków.");
            else
                System.out.println("Spóźniłeś się. Oferta wygasła.");
        }
        else
            System.out.println("Przykro mi!");


    }




}
