package Bilkay.BilUber;

import Bilkay.UserRelatedServices.user;

public class Lift {
    private int driverID;
    private String driverNameSurname;
    private String timeDate;
    private Route route;
    private int availableSeat;
    private int idOfTheLift;


    // Constructor
    public Lift(int idOfTheLift, String driverNameUsername, int driverID, String timeDate, Route route, int availableSeat) {
        this.idOfTheLift = idOfTheLift;
        this.driverNameSurname = driverNameUsername;
        this.driverID = driverID;
        this.timeDate = timeDate;
        this.route = route;
        this.availableSeat = availableSeat;
    }

    public Lift(String driverNameUsername, int driverID, String timeDate, Route route, int availableSeat) {
        this.driverNameSurname = driverNameUsername;
        this.driverID = driverID;
        this.timeDate = timeDate;
        this.route = route;
        this.availableSeat = availableSeat;
    }

    public int getIdOfTheLift() {
        return idOfTheLift;
    }

    public void setIdOfTheLift(int idOfTheLift) {
        this.idOfTheLift = idOfTheLift;
    }

    public String getDriverNameSurname() {
        return driverNameSurname;
    }

    public void setDriverNameSurname(String driverNameSurname) {
        this.driverNameSurname = driverNameSurname;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    // toString method
    public String toString() {
        return this.getDriverNameSurname() + " | " + this.route + " | At: " + this.timeDate + " | "  + " Available Seats: " + this.availableSeat;
    }
}
