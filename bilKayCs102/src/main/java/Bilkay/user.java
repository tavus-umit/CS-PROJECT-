package Bilkay;

import java.util.List;

public class user {

    private static int userIDGenerator = 1;
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

    private List<Category> chosenCategories;

    private List<SubCategory> chosenSubCategories;

    //TODO longblob java ????


    public user(String nameSurname, String username, String password, String webmail, List<Category> chosenCategories, List<SubCategory> chosenSubCategories) {
        this.userID = userIDGenerator;
        userIDGenerator++;
        this.nameSurname = nameSurname;
        this.username = username;
        this.password = password;
        this.webmail = webmail;
        this.chosenCategories = chosenCategories;
        this.chosenSubCategories = chosenSubCategories;
    }
}
