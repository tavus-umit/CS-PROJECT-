package Bilkay.BilUber;
import Bilkay.Category;
import Bilkay.SubCategory;
import Bilkay.user;

import java.util.ArrayList;

public class Driver extends user {

    // Instance variables
    private ArrayList<Lift> myLifts;

    // Constructor
    public Driver(int userID, String nameSurname, String username, String password, String webmail,
                  ArrayList<Category> chosenCategories, ArrayList<SubCategory> chosenSubCategories,
                  String role)
    {
        super(userID,nameSurname,  username,  password,  webmail, chosenCategories,  chosenSubCategories, role);
        this.myLifts = new ArrayList<Lift>();
    }
    public Driver(user user)
    {
        super(user.getUserID(),user.getNameSurname(),  user.getUsername(),  user.getPassword(),  user.getWebmail(), user.getChosenCategories(),  user.getChosenSubCategories(), user.getRole());
        this.myLifts = new ArrayList<Lift>();
    }

    // Getter
    public ArrayList<Lift> getMyLifts() {
        return myLifts;
    }

    // This method creates a new journey and adds it to the journeys arraylist
    public void journeyCreator(Date date, Time time, Route route, int fee)
    {
        Lift newLift = new Lift(this, date, time, route, fee);
        System.out.println(newLift);
        this.myLifts.add(newLift);
        System.out.println("New journey has been created to journey list successfully!");

    }
    public void journeyUploader()
    {
        for (int i = 0; i < this.myLifts.size() ; i++)
        {
            BilUber.availableLifts.add(this.myLifts.get(i));
        }
        System.out.println("Your journey list is uploaded to available journey list successfully!");
    }
}
