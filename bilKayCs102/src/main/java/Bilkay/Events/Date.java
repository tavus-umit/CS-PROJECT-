package Bilkay.Events;
public class Date {
    public int day;
    public int monthNo;
    public int year;
    public String monthName;

    public Date(int day, int month, int year) {
        this.day=day;
        this.monthNo=month;
        switch (monthNo) {
            case 1: this.monthName = "January";
                break;
            case 2: this.monthName = "February";
                break;
            case 3: this.monthName = "March";
                break;
            case 4: this.monthName = "April";
                break;
            case 5: this.monthName = "May";
                break;
            case 6: this.monthName = "June";
                break;
            case 7: this.monthName = "July";
                break;
            case 8: this.monthName = "August";
                break;
            case 9: this.monthName = "September";
                break;
            case 10: this.monthName = "October";
                break;
            case 11: this.monthName = "November";
                break;
            case 12:this.monthName = "December";
                break;
        }
        this.year=year;
    }
    public String toString()
    {
        return this.day + "/" + this.monthName + "/" + this.year;
    }
}
