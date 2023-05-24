package Bilkay.BilUber;

import Bilkay.UserRelatedServices.Category;
import Bilkay.UserRelatedServices.SubCategory;
import Bilkay.UserRelatedServices.user;

import java.util.ArrayList;

public class Passenger extends user {
    private ArrayList<Lift> registeredLifts;

    // Constructor
    public Passenger(int userID, String nameSurname, String username, String password, String webmail,
                     ArrayList<Category> chosenCategories, ArrayList<SubCategory> chosenSubCategories,
                     String role) {
        super(userID, nameSurname, username, password, webmail, chosenCategories, chosenSubCategories, role);
        this.registeredLifts = new ArrayList<Lift>();
    }

    public Passenger(user user) {
        super(user.getUserID(), user.getNameSurname(), user.getUsername(), user.getPassword(), user.getWebmail(), user.getChosenCategories(), user.getChosenSubCategories(), user.getRole());
        this.registeredLifts = new ArrayList<Lift>();
    }

    public ArrayList<Lift> getRegisteredLifts() {
        return registeredLifts;
    }

    public void setRegisteredLifts(ArrayList<Lift> registeredLifts) {
        this.registeredLifts = registeredLifts;
    }

    public void showAvailableLifts()
    {
        String lifts = "";
        for (int i = 0 ; i < BilUber.availableLifts.size(); i++)
        {
            lifts = lifts + BilUber.availableLifts.get(i) + '\n';
        }
        System.out.println("Available Lifts: ");
        System.out.print(lifts);

    }
    public void showRegisteredLifts()
    {
        String lifts = "";
        for (int i = 0 ; i < this.registeredLifts.size(); i++)
        {
            lifts = lifts + this.registeredLifts.get(i) + '\n';
        }
        System.out.println("Registered Lifts: ");
        System.out.print(lifts);

    }
    public void registerAJourney(int ID) {

        Lift registeredLift = BilUber.availableLifts.get(ID-1);
        registeredLift.setPassengerNum(registeredLift.getPassengerNum()+1);
        this.registeredLifts.add(registeredLift);

    }
}
