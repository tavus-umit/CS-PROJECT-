package Bilkay.Events;
import Bilkay.UserRelatedServices.Category;
import Bilkay.UserRelatedServices.SubCategory;
import Bilkay.UserRelatedServices.user;

import java.util.ArrayList;

public class EventsParticipant extends user {
    private ArrayList<EventsEvent> registeredEvents;

    // Constructor
    public EventsParticipant(int userID, String nameSurname, String username, String password, String webmail,
                             ArrayList<Category> chosenCategories, ArrayList<SubCategory> chosenSubCategories,
                             String role)
    {
        super(userID,nameSurname,  username,  password,  webmail, chosenCategories,  chosenSubCategories, role);
        this.registeredEvents = new ArrayList<EventsEvent>();
    }
    public EventsParticipant(user user)
    {
        super(user.getUserID(),user.getNameSurname(),  user.getUsername(),  user.getPassword(),  user.getWebmail(), user.getChosenCategories(),  user.getChosenSubCategories(), user.getRole());
        this.registeredEvents = new ArrayList<EventsEvent>();
    }

    public ArrayList<EventsEvent> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(ArrayList<EventsEvent> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public void registerAnEvent()
    {


    }
}
