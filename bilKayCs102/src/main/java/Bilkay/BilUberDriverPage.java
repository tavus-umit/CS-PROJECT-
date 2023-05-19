package Bilkay;

import Bilkay.BilUber.BilUberFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BilUberDriverPage extends JPanel{
    private JPanel BilUberDriverPageMainPanel;
    private JButton button2;
    private JButton PassengerButton;
    private JList <Lift> DriverList;
    private JPanel DriverLiftPanel;
    private JPanel ButtonsPanel;
    private JButton AddLiftButton;
    private JButton RefreshButton;
    private JButton MainPageButton;
    private JButton DriverButton;
    private ArrayList<Lift> myLifts;
    private DefaultListModel model;
    private static user user;
    private static Driver driver;
    private static AddLiftPage addLiftPage;
    private BilUberMainPage mainPage;
    private BilUberPassengerPage passengerPage;
    private BilUberFrame frame;

    // Constructor
    public BilUberDriverPage(user newUser)
    {
        user = newUser;
        driver = new Driver(user);
        addLiftPage = new AddLiftPage(driver, this);
        this.mainPage = null;
        this.passengerPage = null;
        this.frame = null;
        this.myLifts = driver.getMyLifts();
        this.model = new DefaultListModel<>();
        this.DriverList.setModel(this.model);

        liftLister();
        AddLiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addLiftPage.setContentPane(addLiftPage.getAddLiftPageMainPanel());
                addLiftPage.setSize(300,600);
                addLiftPage.setTitle("Add A New Lift");
                addLiftPage.setVisible(true);

            }
        });
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getMyLifts().size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "There are not any lifts that you ordered", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                }
                liftLister();
            }
        });
        MainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setContentPane(getMainPage().getBilUberMainPanel());
            }
        });
        PassengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setContentPane(getPassengerPage().getBilUberPassengerPageMainPanel());
            }
        });
    }

    // Getters and Setters
    public JPanel getBilUberDriverPageMainPanel() {
        return BilUberDriverPageMainPanel;
    }

    public void setBilUberDriverPageMainPanel(JPanel bilUberMainPanel) {
        BilUberDriverPageMainPanel = bilUberMainPanel;
    }
    public JList getDriverList() {
        return DriverList;
    }
    public void setDriverList(JList<Lift> driverList) {
        this.DriverList = driverList;
    }

    public JPanel getDriverLiftPanel() {
        return DriverLiftPanel;
    }

    public void setDriverLiftPanel(JPanel driverLiftPanel) {
        DriverLiftPanel = driverLiftPanel;
    }

    public JPanel getButtonsPanel() {
        return ButtonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        ButtonsPanel = buttonsPanel;
    }

    public ArrayList<Lift> getMyLifts() {
        return myLifts;
    }

    public void setMyLifts(ArrayList<Lift> myLifts) {
        this.myLifts = myLifts;
    }

    public static Bilkay.user getUser() {
        return user;
    }

    public static void setUser(Bilkay.user newUser) {
        user = newUser;
    }

    public BilUberMainPage getMainPage() {
        return mainPage;
    }

    public void setMainPage(BilUberMainPage mainPage) {
        this.mainPage = mainPage;
    }

    public BilUberPassengerPage getPassengerPage() {
        return passengerPage;
    }

    public void setPassengerPage(BilUberPassengerPage passengerPage) {
        this.passengerPage = passengerPage;
    }

    public BilUberFrame getFrame() {
        return frame;
    }

    public void setFrame(BilUberFrame frame) {
        this.frame = frame;
    }

    public void liftLister()
    {
        for(int i = 0; i < this.myLifts.size();i++)
        {
            if(liftChecker(this.myLifts.get(i)))
            {
                this.model.addElement(this.myLifts.get(i));
            }
        }
    }
    public boolean liftChecker(Lift lift)
    {
        for (int i = 0 ; i< this.model.size() ;i++)
        {
            if(lift.equals(this.model.get(i)))
            {
                return false;
            }
        }
        return true;
    }




}
