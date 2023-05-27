package Bilkay;

import java.util.ArrayList;

public class user {

    public static final String[] departmentArray = {"CTIS", "COMD","CS", "GRA", "EEE", "IE", "PHYS", "MATH", "POLS", "AMER", "TRIN", "ME", "IR", "ECON", "MAN","MBG",
            "ELIT", "LAUD", "LAW","HART","IAED","PHIL","PSYC","THEA","TURK","CHEM","MUS","HIST","FA","ARCH"};
    private int userID;
    private String nameSurname;
    private String username;
    private String password;
    private int age;
    private int grade;
    private String webmail;
    private String gender;

    private String department;
    private int bilkayPoints;
    private String role;

    private ArrayList<Category> chosenCategories;

    private ArrayList<SubCategory> chosenSubCategories;

    private ArrayList<user> previouslyMatchedUsers;
    private ArrayList<user> matchRequests;

    //TODO longblob java ????


    public user(int userID, String nameSurname, String username, String password, String webmail,
                ArrayList<Category> chosenCategories, ArrayList<SubCategory> chosenSubCategories,
                String role) {
        this.userID = userID;
        this.role = role;
        this.nameSurname = nameSurname;
        this.username = username;
        this.password = password;
        this.webmail = webmail;
        this.chosenCategories = chosenCategories;
        this.chosenSubCategories = chosenSubCategories;
        this.previouslyMatchedUsers = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public String getWebmail() {
        return webmail;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getBilkayPoints() {
        return bilkayPoints;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<Category> getChosenCategories() {
        return chosenCategories;
    }

    public ArrayList<SubCategory> getChosenSubCategories() {
        return chosenSubCategories;
    }

    public void sendMatchRequest(user u) {
        u.receiveMatchRequest(this);
    }

    public void receiveMatchRequest(user u) {
        matchRequests.add(u);
        System.out.println(this.nameSurname + " received a match request from " + u.getNameSurname());
    }

    public void printMatchRequests() {
        System.out.println("Match requests for " + this.nameSurname + ":");
        for (user user : matchRequests) {
            System.out.println("- " + user.getNameSurname() );
        }
    }

    public void acceptMatchRequest(user u) {
        if (matchRequests.contains(u)) {
            matchRequests.remove(u);
            //adding each user to the others previouslyMatched arraylist
            previouslyMatchedUsers.add(u);
            u.previouslyMatchedUsers.add(this);

            System.out.println(this.nameSurname + " accepted the match request from " + u.getNameSurname());
        }
    }

    public void declineMatchRequest(user u) {
        if (matchRequests.contains(u)) {
            matchRequests.remove(u);
            System.out.println(this.nameSurname + " declined the match request from " + u.getNameSurname());
        }
    }
}
