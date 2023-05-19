package Bilkay;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static int Matcher(user userToBeMatched, user otherUser){
        int catScore=0;
        int subcatScore=0;

        //checks for category matches
        for (Category cat1 : userToBeMatched.getChosenCategories()){

            for (Category cat2 : otherUser.getChosenCategories()){
                if(cat1.equals(cat2)){
                    catScore++;
                }
            }

        }
        //checks for subcategory matches
        for (SubCategory subcat1 : userToBeMatched.getChosenSubCategories()){

            for (SubCategory subcat2 : otherUser.getChosenSubCategories()){
                if(subcat1.equals(subcat2)){
                    subcatScore+=3;
                }
            }

        }
        return catScore+subcatScore;

    }

    public static HashMap<user,Integer>  createNomineeList(user userToBeMatched, user[] otherUser){
        HashMap<user,Integer> userxscore = new HashMap<>();

        for (int i = 0; i < otherUser.length; i++) {
            userxscore.put(otherUser[i],Matcher(userToBeMatched,otherUser[i]));

        }
    }
}
