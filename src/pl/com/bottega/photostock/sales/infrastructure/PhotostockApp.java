package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.infrastructure.repositories.inMemoryClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.inMemoryLightboxRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.inMemoryProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.inMemoryReservationRepository;
import pl.com.bottega.photostock.sales.model.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositories.LightboxRepository;
import pl.com.bottega.photostock.sales.model.repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.repositories.ReservationRepository;
import pl.com.bottega.photostock.sales.ui.*;

import java.util.Scanner;

public class PhotostockApp {

    public static void main(String[] args) {
        new PhotostockApp().start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        LightboxRepository lightBoxRepository = new inMemoryLightboxRepository();
        ClientRepository clientRepository = new inMemoryClientRepository();
        ProductRepository productRepository = new inMemoryProductRepository();
        ReservationRepository reservationRepository = new inMemoryReservationRepository();
        LightBoxManagement lightBoxManagement = new LightBoxManagement(lightBoxRepository, clientRepository,
                productRepository, reservationRepository);
        AuthenticationManager authenticationManager = new AuthenticationManager(clientRepository);
        AddProductToLightBoxScreen addProductToLightBoxScreen = new AddProductToLightBoxScreen(lightBoxManagement, scanner);
        LightBoxManagementScreen lightBoxManagementScreen = new LightBoxManagementScreen(scanner, lightBoxManagement,
                authenticationManager, addProductToLightBoxScreen);
        ProductCatalog productCatalog = new ProductCatalog(productRepository);
        SearchScreen searchScreen = new SearchScreen(scanner, authenticationManager, productCatalog);
        MainScreen mainScreen = new MainScreen(scanner, lightBoxManagementScreen, searchScreen);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(scanner, authenticationManager);

        authenticationScreen.show();
        mainScreen.show();
    }

}