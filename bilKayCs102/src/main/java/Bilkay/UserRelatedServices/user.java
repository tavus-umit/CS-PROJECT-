package Bilkay.UserRelatedServices;

import Bilkay.BilUber.Lift;

import java.util.ArrayList;

public class user {

    public static final String[] departmentArray = {"CTIS", "COMD", "CS", "GRA", "EEE", "IE", "PHYS", "MATH", "POLS", "AMER", "TRIN", "ME", "IR", "ECON", "MAN", "MBG",
            "ELIT", "LAUD", "LAW", "HART", "IAED", "PHIL", "PSYC", "THEA", "TURK", "CHEM", "MUS", "HIST", "FA", "ARCH"};
    private final int userID;
    private final String webmail;
    public ArrayList<Lift> providedLiftsAsADriver;
    public ArrayList<Lift> usedLiftsAsAUser;
    public String pathToPP;
    private String nameSurname;
    private String username;
    private String password;
    private int age;
    private String grade;
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

    public String getPathToPP() {
        return pathToPP;
    }

    public void setPathToPP(String pathToPP) {
        this.pathToPP = pathToPP;
    }

    public ArrayList<Lift> getProvidedLiftsAsADriver() {
        return providedLiftsAsADriver;
    }

    public void setProvidedLiftsAsADriver(ArrayList<Lift> providedLiftsAsADriver) {
        this.providedLiftsAsADriver = providedLiftsAsADriver;
    }

    public ArrayList<Lift> getUsedLiftsAsAUser() {
        return usedLiftsAsAUser;
    }

    public void setUsedLiftsAsAUser(ArrayList<Lift> usedLiftsAsAUser) {
        this.usedLiftsAsAUser = usedLiftsAsAUser;
    }

    public int getUserID() {
        return userID;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getWebmail() {
        return webmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getBilkayPoints() {
        return bilkayPoints;
    }

    public void setBilkayPoints(int bilkayPoints) {
        this.bilkayPoints = bilkayPoints;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Category> getChosenCategories() {
        return chosenCategories;
    }

    public void setChosenCategories(ArrayList<Category> chosenCategories) {
        this.chosenCategories = chosenCategories;
    }

    public ArrayList<SubCategory> getChosenSubCategories() {
        return chosenSubCategories;
    }

    public void setChosenSubCategories(ArrayList<SubCategory> chosenSubCategories) {
        this.chosenSubCategories = chosenSubCategories;
    }

}
