package pl.com.bottega.photostock.sales.ui;
import pl.com.bottega.photostock.sales.application.LightBoxManagement;

import java.util.Scanner;


public class AddLightBox {
    private Scanner scanner;
    private LightBoxManagement lightBoxManagement;
    private AuthenticationManager authenticationManager;

    public AddLightBox(Scanner scanner, LightBoxManagement lightBoxManagement,
                       AuthenticationManager authenticationManager){
        this.scanner = scanner;
        this.lightBoxManagement = lightBoxManagement;
        this.authenticationManager = authenticationManager;
    }

    public void show(){
        System.out.println("Podaj nazwę nowego LighBox'a: ");

        String name = scanner.nextLine();
        String clientNumber = authenticationManager.getClientNumber();

        lightBoxManagement.create(clientNumber, name);

        System.out.println(String.format("LightBox %s został dodany.", name));
    }
}
