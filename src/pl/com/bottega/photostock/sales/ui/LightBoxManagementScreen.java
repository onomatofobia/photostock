package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightboxManagement;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;
import java.util.Scanner;

public class LightBoxManagementScreen {

    private LightBoxManagementScreen lightBoxManagementScreen;
    private Scanner scanner;
    private LightboxManagement lightBoxManagement;
    private AuthenticationManager authenticationManager;

    public LightBoxManagementScreen(Scanner scanner, LightboxManagement lightBoxManagement,
                                    AuthenticationManager authenticationManager){
        this.scanner = scanner;
        this.authenticationManager = authenticationManager;
        this.lightBoxManagementScreen = lightBoxManagementScreen;
    }

    public void show() {
        System.out.println("Twoje lajt boxy.");
        List<LightBox> lightBoxes = lightBoxManagement.getLightBoxes(authenticationManager.getClientNumber());
        if (lightBoxes.isEmpty())
            System.out.println("Nie masz aktualnie żadnych lajt boxów");
        else{
            int index = 1;
            for (LightBox lightBox : lightBoxes)
                System.out.println(String.format("%d %s,", index++, lightBox.getName()));
        }

    }
}
