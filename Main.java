public class Main {
    public static void main(String[] args) {
        Account acc1 = new Account(22203691);
        acc1.setAge(19);
        acc1.setDepartment("CS");
        acc1.addUserLike("art");
        acc1.addUserLike("animals");
        acc1.addUserLike("volleyball");
        System.out.println(acc1.toString());

        //gui kısmı test main'i
    
        IDandPasswords idandPasswords = new IDandPasswords();
                    
        LoginPage loginPage = new LoginPage(IDandPasswords.getLoginInfo());
    
        
    }
}
