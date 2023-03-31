import java.awt.desktop.SystemSleepEvent;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Scanner;

public class BilUber {
    // Instance Variables

    public static ArrayList<Journey> availableJourneys = new ArrayList<Journey>();

    public static void runBilUber(Account account)
    {
        Scanner in = new Scanner(System.in);
        boolean isMenuOpen = true;
        do {
            System.out.println("==========BIL-UBER==========");
            System.out.println("Please select which service you want to have");
            System.out.println("1-BE A VOLUNTEER BIL-UBER DRIVER");
            System.out.println("2-BE A BIL-UBER PASSENGER");
            int selection = in.nextInt();
            if (selection == 1) {
                Driver newDriver = new Driver(account.BilkentID, account.password);
                System.out.println("Welcome to the journey creator menu");
                System.out.println("Please enter required information to create a journey");

                System.out.print("Date (DD/MM/YYYY): ");
                String dateStr = in.nextLine();
                int day = Integer.parseInt(dateStr.substring(0, 2));
                int month = Integer.parseInt(dateStr.substring(3, 5));
                int year = Integer.parseInt(dateStr.substring(6));
                Date date = new Date(day, month, year);

                System.out.println("");
                System.out.print("Time (HH:MM): ");
                String timeStr = in.nextLine();
                String hours = timeStr.substring(0, 2);
                String minutes = timeStr.substring(3);
                Time time = new Time(hours, minutes);

                System.out.println("");
                System.out.print("From: ");
                String from = in.nextLine();
                System.out.println("");
                System.out.print("To: ");
                String to = in.nextLine();
                Route route = new Route(from, to);

                System.out.println("");
                System.out.print("Fee: ");
                int fee = in.nextInt();

                newDriver.journeyCreator(date, time, route, fee);
                newDriver.journeyUploader();

                System.out.println("Do you want to exit Bil-Uber");
                System.out.println("1-YES");
                System.out.println("2-NO");
                int exit = in.nextInt();
                if(exit==1){isMenuOpen = false;}

            }
            else if (selection == 2) {
                Passenger newPassenger = new Passenger(account.BilkentID, account.password);
                newPassenger.registerAJourney();

                System.out.println("Do you want to exit Bil-Uber");
                System.out.println("1-YES");
                System.out.println("2-NO");
                int exit = in.nextInt();
                if(exit==1){isMenuOpen = false;}
            }
        }while(isMenuOpen);
    }
    public static String displayJourneys()
    {
        if(availableJourneys != null) {
            String journeyList = "";
            int num = 1;
            for (int i = 0; i < availableJourneys.size(); i++) {
                journeyList = journeyList + num + "- " + availableJourneys.get(i) + '\n';
            }
            return journeyList;
        }
        else
        {
            return "There is no any available journeys right now! Please try again later";
        }
    }



}
