package Bilkay;

import java.util.ArrayList;

public class Profile {

    //registration features
    public int BilkentID;
    public String password;

    //interests
    public ArrayList<SubCategory> subCategories;
    public ArrayList<Category> categories;

    //necessary features
    public String name;
    public String surname;
    public String department;
    public String aboutMePart;
    public String yearsInBilkent;
    public int age;

    public Profile(int id, String bilkentMail, String password) {
        this.BilkentID = id ;
        this.password=password;
    }



    public void addInterest(SubCategory s){
        subCategories.add(s);
    }
    public void addInterest(Category c){
        categories.add(c);
    }


}
