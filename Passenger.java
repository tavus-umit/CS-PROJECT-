import java.util.ArrayList;
import java.util.Scanner;

public class Passenger extends Account{
    private ArrayList<Journey> registeredJourneys;

    // Constructor
    public Passenger(String studentid, String password)
    {
        super(studentid, password);
        this.registeredJourneys = new ArrayList<Journey>();
    }

    public void registerAJourney()
    {
        Scanner in = new Scanner(System.in);
        if(BilUber.availableJourneys !=null) {
            BilUber.displayJourneys();
            System.out.println("Please choose a journey to register by typing the number");
            int selection = in.nextInt();
            registeredJourneys.add(BilUber.availableJourneys.get(selection - 1));
            System.out.println("Your registration is completed successfully!");
        }
        else
        {
            BilUber.displayJourneys();
        }

    }
}
