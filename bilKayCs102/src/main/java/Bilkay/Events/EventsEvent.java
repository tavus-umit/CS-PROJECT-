package Bilkay.Events;
public class EventsEvent {
    private EventsAdmin admin;
    private String title;
    private Date date;
    private Time time;
    private String clubDorm;

    // Constructor
    public EventsEvent(EventsAdmin admin,String title, Date date, Time time, String clubDorm ) {
        this.admin = admin;
        this.title = title;
        this.date = date;
        this.time = time;
        this.clubDorm = clubDorm;
    }

    // Getters

    public EventsAdmin getAdmin() {
        return admin;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public String getClubDorm(){
        return this.clubDorm;
    }

    // Setters

    public void setAdmin(EventsAdmin admin) {
        this.admin = admin;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setClubDorm(String clubDorm) {
        this.clubDorm = clubDorm;
    }


    // toString method
    public String toString()
    {
        return this.admin.getNameSurname() + " | " + this.title + " | " + this.date + " | " + this.time + " | " + this.clubDorm;
    }
}
