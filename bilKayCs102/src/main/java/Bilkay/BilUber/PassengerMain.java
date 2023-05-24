package Bilkay.BilUber;

import Bilkay.UserRelatedServices.user;

import java.util.Scanner;

public class PassengerMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        user user = new user(31, "ServetSayar", "theKarıSikici31", "3131", "yüceadalet@karımısiktinya.com", null, null,"Karısı Sikilmiş");
        Passenger pass = new Passenger(user);
        Driver driv = new Driver(user);

        driv.liftCreator(new Date(12,6,2020), new Time("12","45"), new Route("Center", "East"), 31);
        driv.liftCreator(new Date(31,6,2031), new Time("11","30"), new Route("East", "Center"), 31);


        driv.liftUploader();
        pass.showAvailableLifts();

        System.out.println("Type the ID of the lift to register: ");
        int ID = in.nextInt();
        pass.registerAJourney(ID);
        pass.showRegisteredLifts();
        pass.showAvailableLifts();

    }
}
