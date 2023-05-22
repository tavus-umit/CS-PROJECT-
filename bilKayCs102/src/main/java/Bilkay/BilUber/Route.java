package Bilkay.BilUber;

public class Route {
    // Instance Variables
    private String from;
    private String to;

    // Constructor
    public Route(String from, String to) {
        this.from = from;
        this.to = to;
    }

    // Getters
    public String getFrom() {
        return this.from;
    }

    // Setters
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    // toString method
    public String toString() {
        return this.from + "-->" + this.to;
    }
}
