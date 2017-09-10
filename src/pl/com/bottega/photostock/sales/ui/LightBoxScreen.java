package pl.com.bottega.photostock.sales.ui;

import java.util.Scanner;

public class LightBoxScreen {

    private Scanner scanner;
    private LightBoxPresenter lightBoxPresenter = new LightBoxPresenter();
    private MainScreen mainScreen = new MainScreen();

    public LightBoxScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public void show(){

        while (true) {
            showLightBoxMenu();
            int decission = scanner.nextInt();

            switch (decission) {
                case 1:
                    addNewLighBox.show();
                    break;
                case 2:
                    lightBoxPresenter.show();
                    break;
                case 3:
                    mainScreen.show();
                default:
                    System.out.println("Sorry, ale nie rozumiem.");
            }
        }
    }

    private void showLightBoxMenu() {

        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Dodaj nowy LightBox.");
        System.out.println("2. Wyświetl zawartość LightBox'a.");
        System.out.println("3. Wróć do menu.");
        System.out.print("Co chcesz zrobić? ");
    }


}
