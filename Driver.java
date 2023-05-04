import java.util.ArrayList;

public class Driver extends Account{

    // Instance variables
    private ArrayList<Journey> myJourneys;

    // Constructor
    public Driver(String studentid, String password)
    {
        super(studentid, password);
        this.myJourneys = new ArrayList<Journey>();
    }

    // Getter
    public ArrayList<Journey> getMyJourneys() {
        return myJourneys;
    }

    // This method creates a new journey and adds it to the journeys arraylist
    public void journeyCreator(Date date, Time time, Route route, int fee)
    {
        Journey newJourney = new Journey(this, date, time, route, fee);
        this.myJourneys.add(newJourney);
        System.out.println("New journey has been created to journey list successfully!");
    }
    public void journeyUploader()
    {
        for (int i = 0 ; i < this.myJourneys.size() ; i++)
        {
            BilUber.availableJourneys.add(this.myJourneys.get(i));
        }
        System.out.println("Your journey list is uploaded to available journey list successfully!");
    }
}
