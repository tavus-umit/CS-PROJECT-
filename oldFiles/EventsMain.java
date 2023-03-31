// Author Muhammed Ümit Tavus
import java.util.*;
public class EventsMain{

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String eventsMenu = "========================================================"
                            + '\n'
                            + "                     Event Section                    "
                            +'\n'
                            +"========================================================"
                            +'\n'
                            + "1- Create a New Event"
                            + '\n'
                            +"========================================================"
                            + '\n'
                            + "2- Adjust an Event"
                            + '\n'
                            + "========================================================"
                            + '\n'
                            + "3- Attend an Event"
                            + '\n'
                            + "========================================================"
                            + '\n'
                            + "4- Go Back"
                            + '\n'
                            + "========================================================";
                            

        
        boolean menuLoop = true;
        ArrayList <Events> eventsList = new ArrayList <Events>();

        while (menuLoop)
        {
            System.out.println(eventsMenu);
            System.out.print("Choose your operation: ");
            int operation = in.nextInt();

            if(operation == 1)
            {
                System.out.print("Enter the name of the event: ");
                in.nextLine();
                String eventName = in.nextLine();
                
                System.out.print("Enter the name of the club: ");
                String clubName = in.nextLine();
                
                System.out.print("Enter the date of the event in this format (YYYY/MM//DD): "  );
                String date = in.nextLine();
                
                System.out.print("Enter the time of the event in this format (HH:MM) ");
                String time = in.nextLine();
                

                eventsList.add(new Events(eventName, clubName, date, time));

                System.out.println("A new event is created!");

            }

            else if (operation == 2)
            {
                
                String title = '\n'
                                +"Event No" 
                                + '\t' + '\t'
                                + String.format("%-20s%5s", "Event", "Club") 
                                + '\t' +'\t'
                                + String.format("%-20s%5s", "Date", "Time")
                                +'\n'
                                + "==========================================================================================";
                
                String list = "";
                int eventNO = 1;
                for(Events event : eventsList)
                {
                    list = list + eventNO+ '\t' + '\t' + '\t' + event + '\n';
                    eventNO+=1;
                }

                System.out.println(title);
                System.out.println(list);

                System.out.println("Enter the event no of the event that you wish to change: ");
                int no = in.nextInt();

                System.out.print("Enter the name of the event: ");
                in.nextLine();
                String eventName = in.nextLine();
                                
                System.out.print("Enter the name of the club: ");
                String clubName = in.nextLine();
                                
                System.out.print("Enter the date of the event in this format (YYYY/MM//DD): "  );
                String date = in.nextLine();
                                
                System.out.print("Enter the time of the event in this format (HH:MM) ");
                String time = in.nextLine();

                eventsList.get(no-1).setName(eventName);
                eventsList.get(no-1).setClubName(clubName);
                eventsList.get(no-1).setDate(date);
                eventsList.get(no-1).setTime(time);

                System.out.println("The event is successfully changed!");
                                


            }

            else if(operation ==3)
            {
                String title = '\n'
                +"Event No" 
                + '\t' + '\t'
                + String.format("%-20s%5s", "Event", "Club") 
                + '\t' +'\t'
                + String.format("%-20s%5s", "Date", "Time")
                +'\n'
                + "==========================================================================================";

                String list = "";
                int eventNO = 1;
                for(Events event : eventsList)
                {
                    list = list + eventNO+ '\t' + '\t' + '\t' + event + '\n';
                    eventNO+=1;
                }
                System.out.println(title);
                System.out.println(list);
                
                System.out.println("Enter the event no of the event that you wish to attend ");
                int no = in.nextInt();

                // Account class is needed
                String userName = "username";

                eventsList.get(no-1).addParticipant(userName);

                System.out.println("You successfully joined the event");

            }

            else if (operation == 4)
            {
                menuLoop = false;
            }
        }
    }

}