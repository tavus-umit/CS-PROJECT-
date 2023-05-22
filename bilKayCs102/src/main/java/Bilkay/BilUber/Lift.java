package Bilkay.BilUber;

public class Lift {
    private Driver driver;
    private Date date;
    private Time time;
    private Route route;
    private int fee;

    // Constructor
    public Lift(Driver driver, Date date, Time time, Route route, int fee) {
        this.driver = driver;
        this.date = date;
        this.time = time;
        this.route = route;
        this.fee = fee;
    }

    // Getters

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return this.time;
    }

    // Setters

    public void setTime(Time time) {
        this.time = time;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getFee() {
        return this.fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }


    // toString method
    public String toString() {
        return this.driver.getNameSurname() + " | " + this.route + " | " + this.date + " | " + this.time + " | " + this.fee + "₺";
    }
}
