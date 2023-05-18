package Bilkay;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Passenger extends user{
    private ArrayList<Lift> registeredLifts;

    // Constructor
    public Passenger(int userID,String nameSurname, String username, String password, String webmail,
                  ArrayList<Category> chosenCategories, ArrayList<SubCategory> chosenSubCategories,
                  String role)
    {
        super(userID,nameSurname,  username,  password,  webmail, chosenCategories,  chosenSubCategories, role);
        this.registeredLifts = new ArrayList<Lift>();
    }
    public Passenger(user user)
    {
        super(user.getUserID(),user.getNameSurname(),  user.getUsername(),  user.getPassword(),  user.getWebmail(), user.getChosenCategories(),  user.getChosenSubCategories(), user.getRole());
        this.registeredLifts = new ArrayList<Lift>();
    }

    public ArrayList<Lift> getRegisteredLifts() {
        return registeredLifts;
    }

    public void setRegisteredLifts(ArrayList<Lift> registeredLifts) {
        this.registeredLifts = registeredLifts;
    }

    public void registerAJourney()
    {


    }
}
