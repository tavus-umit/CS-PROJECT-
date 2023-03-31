// Author Muhammed Ümit Tavus
import java.util.*;
public class Events{
    
    // Instance Variables

    private String name;
    private String clubName;
    private String date;
    private String time;
    private ArrayList <String> participants;
    

    // Constructor
    public Events (String name , String clubName , String date , String time)
    {
        this.name = name;
        this.clubName = clubName;
        this.date = date;
        this.time = time;
        this.participants = new ArrayList <String>();
    }

    // Getters

    public String getName()
    {
        return this.name;
    }

    public String getClubName()
    {
        return this.clubName;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getTime()
    {
        return this.time;
    }

    public int getParticipantNum()
    {
        return this.participants.size();
    }

    //Setters

    public void setName(String name)
    {
        this.name = name;
    }

    public void setClubName(String clubName)
    {
        this.clubName = clubName;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
    public void setTime(String time)
    {
        this.time = time;
    }

    public void addParticipant(String participant)
    {
        this.participants.add(participant);
    }

    public void deleteParticipant(String participant)
    {
        this.participants.remove(participant);
    }

    // toString

    public String toString()
    {
        String list =  String.format("%-20s%5s", this.getName(), this.getClubName()) + '\t' + '\t' + String.format("%-20s%5s", this.getDate(), this.getTime());
        return list;
    }
        
    

}