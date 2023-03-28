public class Profile {

    //registration features
    public String BilkentID;
    public String password;

    //necessary features
    public String name;
    public String department;
    public String aboutMePart;
    public String yearsInBilkent;
    public int age;

    public Profile(String id, String password) {
        this.BilkentID = id ;
        this.password=password;
        IDandPasswords.logininfo.put(id, password);
    }

    public boolean setFeatures(String name, String department,
                               String aboutMePart, String ){
        this.name =name;
        
        return true;
    }
}
