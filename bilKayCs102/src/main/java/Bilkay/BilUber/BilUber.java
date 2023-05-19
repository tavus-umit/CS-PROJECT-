package Bilkay.BilUber;
import Bilkay.*;

import java.util.ArrayList;

public class BilUber {
    // Instance Variables

    public static ArrayList<Lift> availableLifts = new ArrayList<Lift>();

    public static void runBilUber(user user) {
        BilUberMainPage mainPage = new BilUberMainPage(user);
        BilUberDriverPage driverPage = new BilUberDriverPage(user);
        BilUberPassengerPage passengerPage = new BilUberPassengerPage(user);

        mainPage.setDriverPage(driverPage);
        mainPage.setPassengerPage(passengerPage);
        driverPage.setMainPage(mainPage);
        driverPage.setPassengerPage(passengerPage);
        passengerPage.setMainPage(mainPage);
        passengerPage.setDriverPage(driverPage);

        BilUberFrame biluber = new BilUberFrame(mainPage, driverPage);
        biluber.setContentPane(mainPage.getBilUberMainPanel());
        mainPage.setFrame(biluber);
        driverPage.setFrame(biluber);
        passengerPage.setFrame(biluber);

    }
}
