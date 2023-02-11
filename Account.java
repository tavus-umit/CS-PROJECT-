import java.util.ArrayList;

public class Account {

    public String userName;
    public  static ArrayList<String> userNamesArrayList = new ArrayList<>();

    public String realName;

    public int BilkentID;
    public ArrayList<String> IDArrayList = new ArrayList<>();

    public String password;

    public String department;
    public static final String[] departmentArray = {"CTIS", "COMD","CS", "GRA", "EEE", "IE", "PHYS", "MATH", "POLS", "AMER", "TRIN", "ME", "IR", "ECON", "MAN","MBG", 
    "ELIT", "LAUD", "LAW","HART","IAED","PHIL","PSYC","THEA","TURK","CHEM","MUS","HIST","FA","ARCH"};

    public String aboutMePart;
    public String yearsInBilkent;
    public int age;
    //public static String 
    
    //array for all possible likes for users to choose theirs from.
    public static final String[] likes = {"voleyball", "tennis", "cinema", "art", "science", "football","animals", "writing", "literature", "blogging", 
    "photography", "travel", "sports/fitness", "reading", "yoga", "dance","acting","journaling", "cooking", "calligraphy", "collectioner", "embroidery",
    "community activism", "card & board games", "cosplaying", "decoration", "drama","drawing","painting","engraving", "coding","model building", "nail art",
    "make up","manga books","origami","animes","sit-coms", "poetry", "puppetry" ,"Rubik's cube","sculpting","knitting", "tattoo","thrifting", "shopping", 
    "game development", };
    
    //arraylist containing the users's own likes
    public ArrayList<String> usersLikes = new ArrayList<>();

    //constructor, takes only Bilkent id as parameter.
    public Account(int studentid){
        this.BilkentID = studentid ;
    }

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


    public String toString(){
        return ("\n WELCOME TO YOUR BILKO ACCOUNT! "+ "\n" +
                "********************************" + "\n"+
                "Username: "+this.userName + "\n" + 
                "Real name: " + this.realName + "\n" +
                "Bilkent ID: " + this.BilkentID + "\n" +
                "Department:" + this.department + "\n" +
                "age: " + this.age + "\n" +
                "Likes: " + this.usersLikes.toString()
               );
    }


}
