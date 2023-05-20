package Bilkay.Events;
import java.util.ArrayList;

import Bilkay.UserRelatedServices.Category;
import Bilkay.UserRelatedServices.SubCategory;
import Bilkay.UserRelatedServices.user;

public class EventsAdmin extends user {

    // Instance variables
    private ArrayList<EventsEvent> myEvents;

    // Constructor
    public EventsAdmin(int userID, String nameSurname, String username, String password, String webmail,
                       ArrayList<Category> chosenCategories, ArrayList<SubCategory> chosenSubCategories,
                       String role)
    {
        super(userID,nameSurname,  username,  password,  webmail, chosenCategories,  chosenSubCategories, role);
        this.myEvents = new ArrayList<EventsEvent>();
    }
    public EventsAdmin(user user)
    {
        super(user.getUserID(),user.getNameSurname(),  user.getUsername(),  user.getPassword(),  user.getWebmail(), user.getChosenCategories(),  user.getChosenSubCategories(), user.getRole());
        this.myEvents = new ArrayList<EventsEvent>();
    }

    // Getter
    public ArrayList<EventsEvent> getMyEvents() {
        return myEvents;
    }

    // This method creates a new event and adds it to the events arraylist
    public void eventCreator(String title, Date date, Time time, String clubDorm)
    {
        EventsEvent newLift = new EventsEvent(this, title, date, time, clubDorm);
        this.myEvents.add(newLift);

    }
    public void eventUploader()
    {
        for (int i = 0; i < this.myEvents.size() ; i++)
        {
            Events.availableEvents.add(this.myEvents.get(i));
        }
        System.out.println("Your journey list is uploaded to available journey list successfully!");
    }
}
