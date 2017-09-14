package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;
import java.util.Scanner;

public class LightBoxPresenterScreen {


    private Scanner scanner;
    private LightBoxPresenter lightBoxPresenter;
    private LightBoxManagementScreen lightBoxManagementScreen;
    private AddProductScreen addProductScreen;
    private LightBoxManagement lightBoxManagement;
    private AuthenticationManager authenticationManager;

    public LightBoxPresenterScreen(Scanner scanner, LightBoxPresenter lightBoxPresenter,
                                   LightBoxManagement lightBoxManagement,
                                   LightBoxManagementScreen lightBoxManagementScreen,
                                   AddProductScreen addProductScreen, LightBoxManagement lightboxManagement,
                                   AuthenticationManager authenticationManager) {
        this.scanner = scanner;
        this.lightBoxPresenter = lightBoxPresenter;
        this.lightBoxManagementScreen = lightBoxManagementScreen;
        this.addProductScreen = addProductScreen;
        this.lightBoxManagement = lightboxManagement;
        this.authenticationManager = authenticationManager;
    }


    public void show() {

        List<LightBox> lightBoxes = lightBoxManagement.getLightBoxes(authenticationManager.getClientNumber());

        if (lightBoxes.isEmpty())
            throw new NullPointerException("Nie masz żadnych LightBox'ów");

        System.out.println("Podaj index Lightbox'a: ");
        int index = scanner.nextInt();

        LightBox lightBox = lightBoxes.get(index);

        lightBoxPresenter.show(lightBox);

        showMenu();

        loop:
        while (true) {
            int decission = scanner.nextInt();

            switch (decission) {
                case 1:
                    addProductScreen.show(lightBox);
                    break;
                case 2:
                    lightBoxManagementScreen.show();
                    break;
                default:
                    System.out.println("Sorry, ale nie rozumiem.");
            }
        }
    }


    private void showMenu() {
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Dodaj produkt do LightBox'a");
        System.out.println("2. Wróć do poprzedniego menu.");
        System.out.print("Twój wybór: ");
    }
}
