package Bilkay.Events;

public class EventsEvent {
    private EventsAdmin admin;
    private String title;
    private Date date;
    private Time time;
    private String clubDorm;

    // Constructor
    public EventsEvent(EventsAdmin admin, String title, Date date, Time time, String clubDorm) {
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

    public void setAdmin(EventsAdmin admin) {
        this.admin = admin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return this.date;
    }

    // Setters

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getClubDorm() {
        return this.clubDorm;
    }

    public void setClubDorm(String clubDorm) {
        this.clubDorm = clubDorm;
    }


    // toString method
    public String toString() {
        return this.admin.getNameSurname() + " | " + this.title + " | " + this.date + " | " + this.time + " | " + this.clubDorm;
    }
}
