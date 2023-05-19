package Bilkay.BilUber;

import javax.swing.*;



public class BilUberFrame extends JFrame {

    public BilUberMainPage mainPage;
    public BilUberDriverPage driverPage;

    BilUberFrame(BilUberMainPage mainPage, BilUberDriverPage driverPage)
    {
        this.mainPage = mainPage;
        this.driverPage = driverPage;
        this.setTitle("BilUber");
        this.setSize(1200,1800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
