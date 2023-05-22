package Bilkay.BilUber;

public class Date {
    public int day;
    public int monthNo;
    public int year;
    public String monthName;

    public Date(int day, int month, int year) {
        this.day = day;
        this.monthNo = month;
        switch (monthNo) {
            case 1 -> this.monthName = "January";
            case 2 -> this.monthName = "February";
            case 3 -> this.monthName = "March";
            case 4 -> this.monthName = "April";
            case 5 -> this.monthName = "May";
            case 6 -> this.monthName = "June";
            case 7 -> this.monthName = "July";
            case 8 -> this.monthName = "August";
            case 9 -> this.monthName = "September";
            case 10 -> this.monthName = "October";
            case 11 -> this.monthName = "November";
            case 12 -> this.monthName = "December";
        }
        this.year = year;
    }

    public String toString() {
        return this.day + "/" + this.monthName + "/" + this.year;
    }
}
