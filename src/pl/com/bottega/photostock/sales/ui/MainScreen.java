package pl.com.bottega.photostock.sales.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainScreen {

    private LightBoxManagementScreen lightBoxManagementScreen;
    private SearchScreen searchScreen;
    private Scanner scanner;
    private Menu menu;
    private List<MenuItem> items = new ArrayList<>();
    private String title;

    public MainScreen(Scanner scanner, LightBoxManagementScreen lightBoxManagementScreen, SearchScreen searchScreen){
        this.scanner = scanner;
        this.lightBoxManagementScreen = lightBoxManagementScreen;
        this.searchScreen = searchScreen;
    }

    public void show() {
        menu = new Menu(scanner);
        menu.setTitleLabel("WITAJ W PHOTOSTOCK!");
        menu.addItem("Wyszukaj produkt.", () -> searchScreen.show());
        menu.addItem("LightbBox'y.", () -> lightBoxManagementScreen.show());
        menu.setLastItemLabel("Zakończ.");
        menu.show();
    }
}
