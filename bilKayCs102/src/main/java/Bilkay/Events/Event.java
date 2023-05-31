package Bilkay.Events;

public class Event {
    private int creatorID;
    private String creatorNameSurname;
    private String timeDate;
    private int eventCapacity;
    private int idOfTheEvent;

    private String nameOfTheEvent;
    private String eventType;
    private String eventLocation;


    public Event(int idOfTheEvent, String creatorNameSurname, int creatorID, String timeDate, int eventCapacity, String nameOfTheEvent, String eventLocation, String eventType) {
        this.creatorID = creatorID;
        this.creatorNameSurname = creatorNameSurname;
        this.timeDate = timeDate;
        this.eventCapacity = eventCapacity;
        this.idOfTheEvent = idOfTheEvent;
        this.nameOfTheEvent = nameOfTheEvent;
        this.eventType = eventType;
        this.eventLocation = eventLocation;
    }

    public Event(String creatorNameSurname, int creatorID, String timeDate, int eventCapacity, String eventLocation, String nameOfTheEvent, String eventType) {
        this.creatorID = creatorID;
        this.creatorNameSurname = creatorNameSurname;
        this.timeDate = timeDate;
        this.eventCapacity = eventCapacity;
        this.nameOfTheEvent = nameOfTheEvent;
        this.eventType = eventType;
        this.eventLocation = eventLocation;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public String getCreatorNameSurname() {
        return creatorNameSurname;
    }

    public void setCreatorNameSurname(String creatorNameSurname) {
        this.creatorNameSurname = creatorNameSurname;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public int getEventCapacity() {
        return eventCapacity;
    }

    public void setEventCapacity(int eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

    public int getIdOfTheEvent() {
        return idOfTheEvent;
    }

    public String getNameOfTheEvent() {
        return nameOfTheEvent;
    }

    public void setNameOfTheEvent(String nameOfTheEvent) {
        this.nameOfTheEvent = nameOfTheEvent;
    }

    public String toString() {
        return "<html>" + creatorNameSurname + " | " + nameOfTheEvent + " | At: " + eventLocation + " | Date: " + timeDate +"<br>"+ " Available Capacity: " + eventCapacity + " | Type: " + eventType +"</html >";
    }
}
