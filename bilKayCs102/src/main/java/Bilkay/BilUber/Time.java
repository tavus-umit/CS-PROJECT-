package Bilkay.BilUber;

public class Time {
    // Instance Variables
    private String hours;
    private String minutes;

    public Time(String hours, String minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    // Getters
    public String getHours() {
        return this.hours;
    }

    // Setters
    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return this.minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    // toString method
    public String toString() {
        return this.hours + ":" + this.minutes;
    }
}
