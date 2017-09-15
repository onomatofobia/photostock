package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;
import java.util.Scanner;

public class LightBoxManagementScreen {

    private Scanner scanner;
    private LightBoxManagement lightBoxManagement;
    private AuthenticationManager authenticationManager;
    private AddProductScreen addProductScreen;
    private LightBoxPresenter lightBoxPresenter;
    private MainScreen mainScreen;
    private List<LightBox> lightBoxes;
    private LightBox lightBox;

    public LightBoxManagementScreen(Scanner scanner, LightBoxManagement lightBoxManagement,
                                    AuthenticationManager authenticationManager,
                                    AddProductScreen addProductScreen){
        this.scanner = scanner;
        this.authenticationManager = authenticationManager;
        this.lightBoxManagement = lightBoxManagement;
        this.addProductScreen = addProductScreen;
    }


    public void show() {
        System.out.println("Twoje LighBoxy:");
        lightBoxes = lightBoxManagement.getLightBoxes(authenticationManager.getClientNumber());
        if (lightBoxes.isEmpty())
            System.out.println("Nie masz aktualnie żadnych LightBoxów.");
        else {
            int index = 1;
            for (LightBox lightBox : lightBoxes)
                System.out.println(String.format("%d %s,", index++, lightBox.getName()));

            }

            menuChoice();
        }


    private void menuChoice(){
        loop:while(true){
        showMenu();
        int decission=scanner.nextInt();
        scanner.nextLine();

        switch(decission){
            case 1:
            addLightBox();
            break loop;
            case 2:
            lightBoxDisplay();
            break;
            case 3:
            mainScreen.show();
            default:
            System.out.println("Sorry, ale nie rozumiem.");
        }
    }
}

    private void lightBoxDisplay() {
        lightBoxes = lightBoxManagement.getLightBoxes(authenticationManager.getClientNumber());

        if (lightBoxes.isEmpty()) {
            System.out.println("Nie masz żadnych LightBox'ów. Dodaj Lightbox.");
            show();
        }

        System.out.println("Podaj index Lightbox'a: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        lightBox = lightBoxes.get(index - 1);
        lightBoxPresenter.show(lightBox);

        showLightBoxMenu();
        lightBoxMenuChoice();
    }

    private void lightBoxMenuChoice(){
        loop:
        while (true) {
            int decission = scanner.nextInt();
            scanner.nextLine();

            switch (decission) {
                case 1:
                    addProductScreen.show(lightBox);
                    break;
                case 2:
                    show();
                    break;
                default:
                    System.out.println("Sorry, ale nie rozumiem.");
            }
        }
    }

    private void addLightBox(){

        System.out.println("Podaj nazwę nowego LighBox'a: ");

        String name = scanner.nextLine();
        String clientNumber = authenticationManager.getClientNumber();

        lightBoxManagement.create(clientNumber, name);

        lightBoxes = lightBoxManagement.getLightBoxes(clientNumber);

        System.out.println(String.format("LightBox %s został dodany.", name));
        show();
    }


    private void showLightBoxMenu() {
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Dodaj produkt do LightBox'a");
        System.out.println("2. Wróć do poprzedniego menu.");
        System.out.print("Twój wybór: ");
    }

    private void showMenu() {
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Dodaj nowy LightBox.");
        System.out.println("2. Wyświetl LightBox.");
        System.out.println("3. Wróć do menu.");
        System.out.print("Twój wybór: ");
    }
}
