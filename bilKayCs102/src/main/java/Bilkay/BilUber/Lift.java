package Bilkay.BilUber;

public class Lift {
    private Driver driver;
    private Date date;
    private Time time;
    private Route route;
    private int fee;
    private int passengerNum;
    private static int classID = 1;
    private int liftID;

    // Constructor
    public Lift(Driver driver, Date date, Time time, Route route, int fee) {
        this.driver = driver;
        this.date = date;
        this.time = time;
        this.route = route;
        this.fee = fee;
        this.passengerNum = 0;
        this.liftID = classID;
        classID++;
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

    public int getPassengerNum() {
        return passengerNum;
    }

    public static int getClassID() {
        return classID;
    }

    public int getLiftID() {
        return liftID;
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

    public void setPassengerNum(int passengerNum) {
        this.passengerNum = passengerNum;
    }

    public static void setClassID(int ID) {
        Lift.classID = ID;
    }

    public void setLiftID(int liftID) {
        this.liftID = liftID;
    }

    // toString method
    public String toString() {
        return this.liftID + " | "+ this.driver.getNameSurname() + " | " + this.route + " | " + this.date + " | " + this.time + " | " + this.fee + "₺" + " | " + "Passenger Number: " + this.passengerNum;
    }
}
