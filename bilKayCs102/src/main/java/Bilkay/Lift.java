package Bilkay;
public class Lift {
    private Driver driver;
    private Date date;
    private Time time;
    private Route route;
    private int fee;

    // Constructor
    public Lift(Driver driver,Date date, Time time, Route route, int fee) {
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

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public Route getRoute() {
        return this.route;
    }

    public int getFee(){
        return this.fee;
    }

    // Setters

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }


    // toString method
    public String toString()
    {
        return this.driver.getNameSurname() + " | " + this.route + " | " + this.date + " | " + this.time + " | " + this.fee + "₺";
    }
}
