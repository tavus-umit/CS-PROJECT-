package Bilkay.BilUber;

import Bilkay.UserRelatedServices.Category;
import Bilkay.UserRelatedServices.SubCategory;
import Bilkay.UserRelatedServices.user;

import java.util.ArrayList;

public class Driver extends user {

    // Instance variables
    private final ArrayList<Lift> myLifts;

    // Constructor
    public Driver(int userID, String nameSurname, String username, String password, String webmail,
                  ArrayList<Category> chosenCategories, ArrayList<SubCategory> chosenSubCategories,
                  String role) {
        super(userID, nameSurname, username, password, webmail, chosenCategories, chosenSubCategories, role);
        this.myLifts = new ArrayList<Lift>();
    }

    public Driver(user user) {
        super(user.getUserID(), user.getNameSurname(), user.getUsername(), user.getPassword(), user.getWebmail(), user.getChosenCategories(), user.getChosenSubCategories(), user.getRole());
        this.myLifts = new ArrayList<Lift>();
    }

    // Getter
    public ArrayList<Lift> getMyLifts() {
        return myLifts;
    }

    // This method creates a new lift and adds it to the lifts arraylist
    public void liftCreator(Date date, Time time, Route route, int fee) {
        Lift newLift = new Lift(this, date, time, route, fee);
        this.myLifts.add(newLift);
    }

    public void liftUploader() {
        for (int i = 0; i < this.myLifts.size(); i++) {
            BilUber.availableLifts.add(this.myLifts.get(i));
        }
    }
}
