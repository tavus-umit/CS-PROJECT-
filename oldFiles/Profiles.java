import java.util.ArrayList;
import java.util.Scanner;

public class Profiles {

    public Profiles[] allProfiles =
    public static ArrayList<Category> Categories
    public static ArrayList<SubCategory> SubCategories

    public String userName;
    public  static ArrayList<String> userNamesArrayList = new ArrayList<>();

    public String realName;
    public String BilkentID;
    public ArrayList<String> IDArrayList = new ArrayList<>();
    public String password;
    public String department;
    public String aboutMePart;
    public String yearsInBilkent;
    public int age;


    public Profiles(String studentid, String userName, String password, String department){
        this.BilkentID = studentid ;
        this.password=password;
        IDandPasswords.logininfo.put(studentid, password);
    }
    //getters
    public String getUserID(){
        return this.BilkentID;
    }
    public String getUserName(){return this.userName;}
    public String getRealName(){return this.realName;}

    //setter methods
    public boolean setRealNameTo(String realname){
        this.realName =realname;
        return true;
    }
    public boolean setUserName(String username){
        if(userNamesArrayList.contains(username)){
            System.out.println("This username already exists.");
            return false;
        }
        else{
            userNamesArrayList.remove(this.userName);
            userNamesArrayList.add(username);
            this.userName = username;
            System.out.println("Username set to:" + username);
            return true;
        }
    }
    public boolean setDepartment(String department){
        boolean found=false;
        for (String n : departmentArray) {
            if (n == department) {
                found = true;
                break;
            }
        }

        if(!found){
            System.out.println("This department does not exist.");
            return false;
        }
        else{
            this.department=department;
            System.out.println("Department set to:" + department);
            return true;
        }
    }
    public boolean setAge(int age){
        this.age = age;
        return true;
    }
    public boolean setPassword(String passw){
        this.password = passw;
        return true;
    }

    public void addUserLike(String like){
        this.usersLikes.add(like);
    }

    public void addAboutMePart(String aboutMe){
        this.aboutMePart = aboutMe;
    }
    public void editAboutMePart() {
        System.out.println(aboutMePart);
        System.out.println("New about me part:");
        Scanner sc = new Scanner(System.in);
        aboutMePart = sc.nextLine();

    }

}
