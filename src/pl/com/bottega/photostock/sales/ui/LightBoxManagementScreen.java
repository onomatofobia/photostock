package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;
import java.util.Scanner;

public class LightBoxManagementScreen {

    private Scanner scanner;
    private LightBoxManagement lightBoxManagement;
    private AuthenticationManager authenticationManager;
    private AddLightBox addLightBox;
    private MainScreen mainScreen;
    private LightBoxPresenterScreen lightBoxPresenterScreen;
    private List<LightBox> lightBoxes;
    private int amount;

    public LightBoxManagementScreen(Scanner scanner, LightBoxManagement lightBoxManagement,
                                    AuthenticationManager authenticationManager){
        this.scanner = scanner;
        this.authenticationManager = authenticationManager;
        this.lightBoxManagement = lightBoxManagement;
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

        switch(decission){
            case 1:
            addLightBox.show();
            break loop;
            case 2:
            lightBoxPresenterScreen.show();
            break;
            case 3:
            mainScreen.show();
            default:
            System.out.println("Sorry, ale nie rozumiem.");
        }
    }
}


    private void showMenu() {
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Dodaj nowy LightBox.");
        System.out.println("2. Wyświetl LightBox.");
        System.out.println("3. Wróć do menu.");
        System.out.print("Twój wybór: ");
    }
}
